package com

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_Oper2_mapPartitions {
  def main(args: Array[String]): Unit = {
    //local模式
    //创建SparkConf对象
    //设定Spark计算框架的运行（部署）环境
    val config = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    //创建Spark上下文对象
    val sc = new SparkContext(config)

    val listRDD: RDD[Int] = sc.makeRDD(1 to 10)

    //mapPartitions效率优于map算子，减少了发送到执行器执行交互次数
    //注意：mapPartitions可能出现内存溢出(OOM)

    //作用：类似于map，但独立地在RDD的每一个分片上运行，因此在类型为T的RDD上运行时，
    // func的函数类型必须是Iterator[T] => Iterator[U]。
    // 假设有N个元素，有M个分区，那么map的函数的将被调用N次,而mapPartitions被调用M次,一个函数一次处理所有分区。
    val mapPartitionsRDD: RDD[Int] = listRDD.mapPartitions(datas => {
      datas.map(data => data * 2)
    })
    mapPartitionsRDD.collect().foreach(println);
  }
}
