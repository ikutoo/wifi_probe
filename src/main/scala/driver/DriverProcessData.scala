package driver

import java.util.Timer

import conf.ConfigurationManager
import constants.Constants
import timer.TimerProcessData
import utils.JDBCHelper

/**
  * Created by Administrator on 2017-05-24.
  */
object DriverProcessData {
  def main(args: Array[String]): Unit = {
    //设置log等级
   // StreamingLogs.setStreamingLogLevels()

    //为单例对象创建实例
    JDBCHelper.getInstanse()
  //  SparkManager.getInstance()

    //执行定时任务
    val timer = new Timer
    val timerProcessData = new TimerProcessData
    timer.schedule(timerProcessData, 0, 1000 * ConfigurationManager.getProperty(Constants.PROCCESS_INTERVAL).toInt)

  }
}
