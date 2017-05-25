package timer

import java.util.TimerTask

import global.{DataPool, SparkManager}
import task.TaskPullData
import util.SparkUtils

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Administrator on 2017-05-24.
  */
class TimerProcessData extends TimerTask {

  //case class Data(row_id: Int, id: String, mmac: String, rate: Int, wssid: String, wmac: String, date: Timestamp, lat: String, lon: String, addr: String)

  def run(): Unit = {
    val startMili = System.currentTimeMillis
    println("//////////开始处理数据////////////////////////////////////////////")
    val sc = SparkManager.getInstance().getSparkContext
    val sql = SparkManager.getInstance().getSqlContext
    val pool = DataPool.getInstance()

    //获取数据
    val task = new TaskPullData
    task.run
    val dataItems = task.getDataItems
    val dataDetailItems = task.getDataDetailItems
    val oldDataItems = pool.getDataItems
    val oldDataDetailItems = pool.getDataDetailItems


    println("dataItems总条数：" + dataItems.size)
    println("dataDetailItems总条数：" + dataDetailItems.size)
    println("oldDataItems总条数：" + oldDataItems.size)
    println("oldDataDetailItems总条数：" + oldDataDetailItems.size)

    val arrDataItems = new ArrayBuffer[String]()
    for (i <- 0 to dataItems.size() - 1) {
      arrDataItems += dataItems.get(i)
    }

    val arrDataDetailItems = new ArrayBuffer[String]()
    for (i <- 0 to dataDetailItems.size() - 1) {
      arrDataDetailItems += dataDetailItems.get(i)
    }

    val arrOldDataItems = new ArrayBuffer[String]()
    for (i <- 0 to oldDataItems.size() - 1) {
      arrOldDataItems += oldDataItems.get(i)
    }

    val arrOldDataDetailItems = new ArrayBuffer[String]()
    for (i <- 0 to oldDataDetailItems.size() - 1) {
      arrOldDataDetailItems += oldDataDetailItems.get(i)
    }

    val rddDataItems = sc.parallelize(arrDataItems)
    val rddDataDetailTiems = sc.parallelize(arrDataDetailItems)
    val rddOldDataItems = sc.parallelize(arrOldDataItems)
    val rddOldDataDetailTiems = sc.parallelize(arrOldDataDetailItems)
    SparkUtils.loadDataToTmpTable(sc, sql, rddDataItems, rddDataDetailTiems, rddOldDataItems, rddOldDataDetailTiems)

    sql.sql("select * from data").show()
    sql.sql("select * from dataDetail").show()
    sql.sql("select * from oldData").show()
    sql.sql("select * from oldDataDetail").show()

    //将数据加入数据池
    pool.clearPool()
    for (i <- 0 to dataItems.size() - 1) {
      pool.addDataItem(dataItems.get(i))
    }
    for (i <- 0 to dataDetailItems.size() - 1) {
      pool.addDataDetailItem(dataDetailItems.get(i))
    }

    val endMili = System.currentTimeMillis
    println("数据处理总耗时为：" + (endMili - startMili) + " 毫秒")
    println("//////////结束处理数据////////////////////////////////////////////")
  }
}
