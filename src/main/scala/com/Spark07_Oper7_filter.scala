package com

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark07_Oper7_filter {
  def main(args: Array[String]): Unit = {
    //local模式
    //创建SparkConf对象
    //设定Spark计算框架的运行（部署）环境
    val config = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    //创建Spark上下文对象
    val sc = new SparkContext(config)

    //生成数据，按照指定的规则进行过滤
    val listRDD: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))

    val filterRDD: RDD[Int] = listRDD.filter(x => x % 2 == 0)

    filterRDD.collect().foreach(println);

  }
}
