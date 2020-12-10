package com

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark05_Oper5_glom {
  def main(args: Array[String]): Unit = {
    //local模式
    //创建SparkConf对象
    //设定Spark计算框架的运行（部署）环境
    val config = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    //创建Spark上下文对象
    val sc = new SparkContext(config)

    //val listRDD: RDD[Int] = sc.makeRDD(1 to 10)
    val listRDD: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8), 2)

    //作用：将每一个分区形成一个数组，形成新的RDD类型时RDD[Array[T]]
    val GlomRDD: RDD[Array[Int]] = listRDD.glom()

//    GlomRDD.collect().foreach(arrs=>{
//      for(arr <- arrs){
//        println(arr)
//      }
//    });
    GlomRDD.collect().foreach(array=>{
      println(array.mkString(","))
    })
  }
}
