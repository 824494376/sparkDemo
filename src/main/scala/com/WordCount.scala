package com

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    //local模式
    //创建SparkConf对象
    //设定Spark计算框架的运行（部署）环境
    val config = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    //创建Spark上下文对象
    val sc = new SparkContext(config)
    //读取文件 ，将文件的内容一行一行的读取出来
    //路径查找位置默认从当前的部署中查找
    //如果需要从本地查找：file:///opt/module/spark/input
    //val lines: RDD[String] = sc.textFile("input")
    val lines: RDD[String] = sc.textFile("file:///opt/module/spark/input")

    val words: RDD[String] = lines.flatMap(_.split(" "))

    val wordToOne: RDD[(String, Int)] = words.map((_, 1))
    val wordToSum: RDD[(String, Int)] = wordToOne.reduceByKey(_ + _)
    val result: Array[(String, Int)] = wordToSum.collect()

    result.foreach(println)
    //println(result)
  }
}
