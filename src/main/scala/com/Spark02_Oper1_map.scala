package com

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_Oper1_map {
  def main(args: Array[String]): Unit = {
    //local模式
    //创建SparkConf对象
    //设定Spark计算框架的运行（部署）环境
    val config = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    //创建Spark上下文对象
    val sc = new SparkContext(config)

    val listRDD: RDD[Int] = sc.makeRDD(1 to 10)

    //. 作用：返回一个新的RDD，该RDD由每一个输入元素经过func函数转换后组成
    val mapRDD: RDD[Int] = listRDD.map(x => x * 2)

    mapRDD.collect().foreach(println)

  }
}
