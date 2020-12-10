package com

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

import scala.util.parsing.json.JSON


object Spark16_JSON {
  def main(args: Array[String]): Unit = {
    //local模式
    //创建SparkConf对象
    //设定Spark计算框架的运行（部署）环境
    val config = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    //创建Spark上下文对象
    val sc = new SparkContext(config)

    val json=sc.textFile("input/user.json")
    val result=json.map(JSON.parseFull)
    JSON.Parser("[]")
    result.foreach(println)
    //释放资源
    sc.stop()

  }
}
