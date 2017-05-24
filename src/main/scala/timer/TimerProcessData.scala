package timer

import java.util.TimerTask

import task.TaskPullData


/**
  * Created by Administrator on 2017-05-24.
  */
class TimerProcessData extends TimerTask {
  def run(): Unit = {
    val startMili = System.currentTimeMillis
    println("//////////开始处理数据////////////////////////////////////////////")

    //获取数据
    val task = new TaskPullData
    task.run
    val dataItems = task.getDataItems
    val dataDetailItems = task.getDataDetailItems

    println(dataItems.size)
    println(dataDetailItems.size)
    println(dataItems)

    val endMili = System.currentTimeMillis
    println("数据处理总耗时为：" + (endMili - startMili) + " 毫秒")
    println("//////////结束处理数据////////////////////////////////////////////")
  }
}
