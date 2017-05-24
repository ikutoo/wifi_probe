package test

import conf.ConfigurationManager
import constants.Constants
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{Partitioner, SparkConf, SparkContext}
import utils.StreamingLogs

import scala.collection.mutable

/**
  * Created by Administrator on 2017-05-22.
  */
object HelloWord {
  def main(args: Array[String]): Unit = {
    StreamingLogs.setStreamingLogLevels()

    val sconf = new SparkConf().setAppName("wifiProbe").setMaster("local[4]").set("spark.testing.memory", "2048000000")
    val sc = new SparkContext(sconf)
    val streamContext = new StreamingContext(sc, Seconds(ConfigurationManager.getInteger(Constants.PROCCESS_INTERVAL)))
    val dStream = streamContext.socketTextStream("127.0.0.1", 8888)
    // 对数据进行处理
    val resultDStream = dStream.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)
    resultDStream.print()
    // 启动spark streaming
    streamContext.start()
    // 等待被停止
    streamContext.awaitTermination()

  }
}
