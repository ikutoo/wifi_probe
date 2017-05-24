package driver

import java.util.Timer

import conf.ConfigurationManager
import constants.Constants
import timer.TimerProcessData

/**
  * Created by Administrator on 2017-05-24.
  */
object DriverProcessData {
  def main(args: Array[String]): Unit = {

    val timer = new Timer
    val timerProcessData = new TimerProcessData
    timer.schedule(timerProcessData, 0, 1000 * ConfigurationManager.getProperty(Constants.PROCCESS_INTERVAL).toInt)

  }
}
