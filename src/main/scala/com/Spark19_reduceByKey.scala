package com

import org.apache.spark.{SparkConf, SparkContext}

object Spark19_reduceByKey {
  def main(args: Array[String]): Unit = {

    val config = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    //创建Spark上下文对象
    val sc = new SparkContext(config)

    val rdd08 = sc.parallelize(List((1, 1),  (1, 4),(1, 3), (3, 7), (3, 5)))
    val rdd08_1 = rdd08.reduceByKey((x, y) => x + y)
    println("reduceByKey 用法 " + rdd08_1.collect().mkString(","))

    sc.stop()
  }
}
