package com

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark12_Oper12_sortBy {
  def main(args: Array[String]): Unit = {
    //local模式
    //创建SparkConf对象
    //设定Spark计算框架的运行（部署）环境
    val config = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    //创建Spark上下文对象
    val sc = new SparkContext(config)

    //缩减分区数：可以简单理解为合并分区
    //val listRDD: RDD[Int] = sc.makeRDD(1 to 16 ,4)
    val listRDD: RDD[Int] = sc.makeRDD(List(1,2,3,4,5,6,7,8,9),4);

    val sortRDD: RDD[Int] = listRDD.sortBy(x => x,false)
    //sortRDD.foreach(println)
    sortRDD.collect().foreach(println);
  }
}
