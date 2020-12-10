package com

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, SparkConf, SparkContext}

object Spark13_Oper13_partitionBy {
  def main(args: Array[String]): Unit = {
    //local模式
    //创建SparkConf对象
    //设定Spark计算框架的运行（部署）环境
    val config = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    //创建Spark上下文对象
    val sc = new SparkContext(config)

    //val listRDD: RDD[Int] = sc.makeRDD(List(1,2,3,4,5,6,7,8,9,1));
    val listRDD: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3)))
    val partitionRDD: RDD[(String, Int)] = listRDD.partitionBy(new MyPartitioner(3))

    partitionRDD.saveAsTextFile("output")
  }
}

class MyPartitioner(partitions:Int) extends Partitioner{
  override def numPartitions: Int = {
    partitions
  }

  override def getPartition(key: Any): Int = {
    1
  }
}
