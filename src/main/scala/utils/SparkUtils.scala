package utils

import java.util

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types._
import org.apache.spark.sql.{Row, SQLContext}

import scala.collection.mutable.ArrayBuffer


object SparkUtils {

  /**
    * 加载数据到临时表
    *
    * @param sc
    * @param sqlContext
    */
  def loadDataToTmpTable(sc: SparkContext, sqlContext: SQLContext, dataRDD: RDD[String], dataDetailRDD: RDD[String]): Unit = {

    val dataSchema = StructType(
      List(
        StructField("row_id", IntegerType, true),
        StructField("id", StringType, true),
        StructField("mmac", StringType, true),
        StructField("rate", IntegerType, true),
        StructField("wssid", StringType, true),
        StructField("wmac", StringType, true),
        StructField("date", LongType, true),
        StructField("lat", StringType, true),
        StructField("lon", StringType, true),
        StructField("addr", StringType, true)
      )
    )

    val dataDetailSchema = StructType(
      List(
        StructField("row_id", IntegerType, true),
        StructField("master_id", IntegerType, true),
        StructField("time", LongType, true),
        StructField("mac", StringType, true),
        StructField("rssi", StringType, true),
        StructField("range", StringType, true),
        StructField("ts", StringType, true),
        StructField("tc", StringType, true),
        StructField("tmc", StringType, true),
        StructField("ds", StringType, true),
        StructField("essid1", StringType, true),
        StructField("essid2", StringType, true),
        StructField("essid3", StringType, true),
        StructField("essid4", StringType, true),
        StructField("essid5", StringType, true),
        StructField("essid6", StringType, true)
      )
    )

    val dataRowRDD = dataRDD.map(_.split(",", -1)).map(s => Row(s(0).toInt, s(1).toString.trim, s(2).toString.trim, s(3).toInt,
      s(4).toString.trim, s(5).toString.trim, DateUtils.stringToLong(s(6).toString.trim), s(7).toString.trim, s(8).toString.trim, s(9).toString.trim))

    val dataDetailRowRDD = dataDetailRDD.map(_.split(",", -1)).map(s => Row(s(0).toInt, s(1).toInt, DateUtils.stringToLong(s(2).toString.trim), s(3).toString.trim,
      s(4).toString.trim, s(5).toString.trim, s(6).toString.trim, s(7).toString.trim, s(8).toString.trim, s(9).toString.trim,
      s(10).toString.trim, s(11).toString.trim, s(12).toString.trim, s(13).toString.trim, s(14).toString.trim, s(15).toString.trim))

    val dataDataFrame = sqlContext.createDataFrame(dataRowRDD, dataSchema).distinct()
    dataDataFrame.registerTempTable("data")


    val dataDetailDataFrame = sqlContext.createDataFrame(dataDetailRowRDD, dataDetailSchema).distinct()
    dataDetailDataFrame.registerTempTable("dataDetail")
  }

  def getRowIds(rowIDs: util.ArrayList[Integer], oldRowIDs: util.ArrayList[Integer]): ArrayBuffer[Int] = {
    val allRowIDs = new ArrayBuffer[Int]()
    if (rowIDs.size() == 0) {
      return allRowIDs
    }
    if (oldRowIDs.size() == 0) {
      allRowIDs += -1
    }
    else {
      if (oldRowIDs.get(oldRowIDs.size() - 1) != rowIDs.get(0)) {
        allRowIDs += oldRowIDs.get(oldRowIDs.size() - 1)
      }
    }

    for (i <- 0 to rowIDs.size() - 1) {
      allRowIDs += rowIDs.get(i)
    }
    allRowIDs
  }
}




