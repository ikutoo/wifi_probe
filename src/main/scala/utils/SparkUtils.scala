package util

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types._
import org.apache.spark.sql.{Row, SQLContext}


object SparkUtils {

  /**
    * 加载数据到临时表
    *
    * @param sc
    * @param sqlContext
    */
  def loadDataToTmpTable(sc: SparkContext, sqlContext: SQLContext, dataRDD: RDD[String], dataDetailRDD: RDD[String],
                         oldDataRDD: RDD[String], oldDataDetailRDD: RDD[String]): Unit = {

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

    val oldDataRowRDD = oldDataRDD.map(_.split(",", -1)).map(s => Row(s(0).toInt, s(1).toString.trim, s(2).toString.trim, s(3).toInt,
      s(4).toString.trim, s(5).toString.trim, DateUtils.stringToLong(s(6).toString.trim), s(7).toString.trim, s(8).toString.trim, s(9).toString.trim))

    val oldDataDetailRowRDD = oldDataDetailRDD.map(_.split(",", -1)).map(s => Row(s(0).toInt, s(1).toInt, DateUtils.stringToLong(s(2).toString.trim), s(3).toString.trim,
      s(4).toString.trim, s(5).toString.trim, s(6).toString.trim, s(7).toString.trim, s(8).toString.trim, s(9).toString.trim,
      s(10).toString.trim, s(11).toString.trim, s(12).toString.trim, s(13).toString.trim, s(14).toString.trim, s(15).toString.trim))

    val dataDataFrame = sqlContext.createDataFrame(dataRowRDD, dataSchema)
    dataDataFrame.registerTempTable("data")

    val dataDetailDataFrame = sqlContext.createDataFrame(dataDetailRowRDD, dataDetailSchema)
    dataDetailDataFrame.registerTempTable("dataDetail")

    val oldDataDataFrame = sqlContext.createDataFrame(oldDataRowRDD, dataSchema)
    oldDataDataFrame.registerTempTable("oldData")

    val oldDataDetailDataFrame = sqlContext.createDataFrame(oldDataDetailRowRDD, dataDetailSchema)
    oldDataDetailDataFrame.registerTempTable("oldDataDetail")
  }

}




