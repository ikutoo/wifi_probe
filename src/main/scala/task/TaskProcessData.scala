package task

import java.util

import conf.ConfigurationManager
import constants.Constants
import dataStructs.{DataDetailItem, DataItem}

/**
  * Created by Administrator on 2017-05-24.
  */
class TaskProcessData(dataItem1: DataItem, dataItem2: DataItem, dataDetailItems1: util.ArrayList[DataDetailItem], dataDetailItems2: util.ArrayList[DataDetailItem]) {

  def run: Unit = {
    var customerFlow = 0
    var inStoreFlow = 0

    if (dataItem1 != null)
      println(dataItem1.toString)
    else
      println("dataItem1 is null")
    println(dataItem2.toString)
    println(dataDetailItems1.size())
    println(dataDetailItems2.size())

    val inMacs = new util.ArrayList[String]()
    val outMacs = new util.ArrayList[String]()
    for (i <- 0 to dataDetailItems2.size() - 1) {
      inMacs.add(dataDetailItems2.get(i).getMac)
    }
    for (i <- 0 to dataDetailItems1.size() - 1) {
      outMacs.add(dataDetailItems1.get(i).getMac)
    }
    val tmpMacs = new util.ArrayList[String]()
    tmpMacs.addAll(inMacs)
    inMacs.removeAll(outMacs)
    outMacs.removeAll(tmpMacs)

    val inDataDetailItems = new util.ArrayList[DataDetailItem]()
    val outDataDetailItems = new util.ArrayList[DataDetailItem]()
    for (i <- 0 to dataDetailItems2.size() - 1) {
      if (inMacs.contains(dataDetailItems2.get(i).getMac))
        inDataDetailItems.add(dataDetailItems2.get(i))
    }
    for (i <- 0 to dataDetailItems1.size() - 1) {
      if (outMacs.contains(dataDetailItems1.get(i).getMac))
        outDataDetailItems.add(dataDetailItems1.get(i))
    }

    //获取设备ID
    val deviceID = dataItem2.getId

    //获取时间
    val dataTime = dataItem2.getDate

    //统计客流量
    customerFlow += inMacs.size()

    //统计入店率
    for (i <- 0 to inDataDetailItems.size() - 1) {
      if (inDataDetailItems.get(i).getRange < ConfigurationManager.getFloat(Constants.INSTORE_DISTANCE))
        inStoreFlow += 1;
    }


    println("")
  }
}
