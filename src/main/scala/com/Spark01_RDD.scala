package com

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_RDD {
  def main(args: Array[String]): Unit = {
    //local模式
    //创建SparkConf对象
    //设定Spark计算框架的运行（部署）环境
    val config = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    //创建Spark上下文对象
    val sc = new SparkContext(config)
    //创建RDD
    //1)从内存中创建makeRDD ,底层实现就是parallelize
    //val listRDD: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))
    //使用自定义分区
    val listRDD: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4),2)
    //2)从内存中创建 parallelize 并行
    //val arrayRDD: RDD[Int] = sc.parallelize(Array(1, 2, 3, 4))
    //3)从外部存储中创建
    //默认情况下，可以读取项目路径，也可以读取其他路径：HDFS
    //默认从文件中读取的数据都是字符串类型
    //val fileRDD: RDD[String] = sc.textFile("input")
    //读取文件时，传递的分区参数为最小区数，但是不一定是这个区数，取决于hadoop读取文件时分片规则
    //val fileRDD: RDD[String] = sc.textFile("input",2)
    //sc.textFile("hdfs://hadoop102:9000/RELEASE") //其他路径读取

    listRDD.saveAsTextFile("output")
    //listRDD.collect().foreach(println);
  }
}
