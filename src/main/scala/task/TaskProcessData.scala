package task

import java.util

import conf.ConfigurationManager
import constants.Constants
import dataStructs._
import utils.DateUtils

/**
  * Created by Administrator on 2017-05-24.
  */
class TaskProcessData(dataItem1: DataItem, dataItem2: DataItem, dataDetailItems1: util.ArrayList[DataDetailItem], dataDetailItems2: util.ArrayList[DataDetailItem]) {

  def run: Unit = {

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
    var dateTime = dataItem2.getDate
    dateTime = DateUtils.removeMinAndSec(dateTime)

    //获取历史数据
    val taskGetDeviceData = new TaskGetDeviceData(deviceID, dateTime)
    taskGetDeviceData.run()
    var deviceData = taskGetDeviceData.getDeviceData
    if (deviceData == null) {
      deviceData = new DeviceData(deviceID, dateTime, 0, 0, 0.0f, 0.0f, 0.0f)
    }

    var customerFlow = deviceData.getCustomerFlow
    var inStoreFlow = deviceData.getInStoreFlow
    //统计客流量
    customerFlow += inMacs.size()

    //统计入店量
    for (i <- 0 to inDataDetailItems.size() - 1) {
      if (inDataDetailItems.get(i).getRange < ConfigurationManager.getFloat(Constants.INSTORE_DISTANCE))
        inStoreFlow += 1;
    }

    //统计入店率
    val inStoreRate = inStoreFlow.toFloat / customerFlow;

    //更新deviceData
    deviceData.setCustomerFlow(customerFlow)
    deviceData.setInStoreFlow(inStoreFlow)
    deviceData.setInStoreRate(inStoreRate)
    val taskUpdateDeviceData = new TaskUpdateDeviceData(deviceData)
    taskUpdateDeviceData.run()

    for (i <- 0 to inDataDetailItems.size() - 1) {
      //创建visitID
      val visitID = System.currentTimeMillis().toInt;
      val customerID = inDataDetailItems.get(i).getMac
      val inTime = inDataDetailItems.get(i).getDateTime

      //获取上次visit数据
      val taskGetLastVisit = new TaskGetLastVisit(deviceID, customerID)
      taskGetLastVisit.run()
      var lastVisit = taskGetLastVisit.getVisit
      val visit = new Visit(visitID, deviceID, customerID, inTime, null, -1, -1)
      if (lastVisit != null) {
        val visitCircle = (inTime.getTime - lastVisit.getInTime.getTime) / 1000
        visit.setVisitCircle(visitCircle.toInt)
      }
      //更新visit表
      val taskUpdateVisit = new TaskUpdateVisit(visit)
      taskUpdateVisit.run()
    }

    for (i <- 0 to outDataDetailItems.size() - 1) {
      val customerID = outDataDetailItems.get(i).getMac
      val leaveTime = outDataDetailItems.get(i).getDateTime

      //获取上次visit数据
      val taskGetLastVisit = new TaskGetLastVisit(deviceID, customerID)
      taskGetLastVisit.run()
      var lastVisit = taskGetLastVisit.getVisit
      if (lastVisit != null) {
        lastVisit.setLeaveTime(leaveTime)
        val stayTime = (leaveTime.getTime - lastVisit.getInTime.getTime) / 1000
        lastVisit.setStayTime(stayTime.toInt)
        //更新visit表
        val taskUpdateVisit = new TaskUpdateVisit(lastVisit)
        taskUpdateVisit.run()
      }
    }



    println("")
  }
}
