package timer

import java.util.TimerTask

import dataStructs.{DataDetailItem, DataItem}
import global.DataPool
import task.{TaskGetEquipments, TaskProcessData, TaskPullData}
import utils.SparkUtils
import java.util

/**
  * Created by Administrator on 2017-05-24.
  */
class TimerProcessData extends TimerTask {

  def run(): Unit = {
    val startMili = System.currentTimeMillis
    println("//////////开始处理数据////////////////////////////////////////////")
    //  val sc = SparkManager.getInstance().getSparkContext
    //  val sql = SparkManager.getInstance().getSqlContext
    val pool = DataPool.getInstance()

    //获取数据
    val task = new TaskPullData
    task.run
    val task2 = new TaskGetEquipments
    task2.run

    val equipments = task2.getEquipments
    for (i <- 0 to equipments.size() - 1) {
      val equipment = equipments.get(i).getEquipment;
      val dataItems = task.getDataItems(equipment)
      val dataDetailItems = task.getDataDetailItems(equipment)
      val rowIDs = task.getRowIDs(equipment)
      val oldDataItems = pool.getDataItems(equipment)
      val oldDataDetailItems = pool.getDataDetailItems(equipment)
      val oldRowIds = pool.getRowIDs(equipment)

      println("equipment:" + equipment)
      println("dataItems总条数：" + dataItems.size)
      println("dataDetailItems总条数：" + dataDetailItems.size)
      println("oldDataItems总条数：" + oldDataItems.size)
      println("oldDataDetailItems总条数：" + oldDataDetailItems.size)
      println("rowIDs总条数：" + rowIDs.size())
      println("oldRowIds总条数：" + oldRowIds.size())

      for (i <- 0 to oldDataItems.size() - 1) {
        dataItems.add(oldDataItems.get(i))
      }
      for (i <- 0 to oldDataDetailItems.size() - 1) {
        dataDetailItems.add(oldDataDetailItems.get(i))
      }

      //生成待处理的rowIDs
      val allRowIDs = SparkUtils.getRowIds(rowIDs, oldRowIds)

      for (i <- 0 to allRowIDs.size - 2) {
        val startID = allRowIDs(i)
        val endID = allRowIDs(i + 1)
        var dataItem1: DataItem = null
        var dataItem2: DataItem = null
        for (i <- 0 to dataItems.size() - 1) {
          if (dataItems.get(i).getRowID == startID)
            dataItem1 = dataItems.get(i)
          else if (dataItems.get(i).getRowID == endID)
            dataItem2 = dataItems.get(i)
        }
        val dataDetailItems1 = new util.ArrayList[DataDetailItem]()
        val dataDetailItems2 = new util.ArrayList[DataDetailItem]()
        for (i <- 0 to dataDetailItems.size() - 1) {
          if (dataDetailItems.get(i).getMasterID == startID)
            dataDetailItems1.add(dataDetailItems.get(i))
          else if (dataDetailItems.get(i).getMasterID == endID)
            dataDetailItems2.add(dataDetailItems.get(i))
        }
        val task = new TaskProcessData(dataItem1, dataItem2, dataDetailItems1, dataDetailItems2)
        task.run
      }

      //将数据加入数据池
      pool.addDataItem(equipment, dataItems);
      pool.addDataDetailItem(equipment, dataDetailItems);
      pool.addRowIDs(equipment, rowIDs)
    }

    val endMili = System.currentTimeMillis
    println("数据处理总耗时为：" + (endMili - startMili) + " 毫秒")
    println("//////////结束处理数据////////////////////////////////////////////")
  }
}
