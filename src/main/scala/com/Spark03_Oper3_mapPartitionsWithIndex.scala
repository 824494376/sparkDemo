package com

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark03_Oper3_mapPartitionsWithIndex {
  def main(args: Array[String]): Unit = {
    //local模式
    //创建SparkConf对象
    //设定Spark计算框架的运行（部署）环境
    val config = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    //创建Spark上下文对象
    val sc = new SparkContext(config)

    //val listRDD: RDD[Int] = sc.makeRDD(1 to 10)
    val listRDD: RDD[Int] = sc.makeRDD(1 to 10,2)

    //1. 作用：类似于mapPartitions，
    // 但func带有一个整数参数表示分片的索引值，因此在类型为T的RDD上运行时，f
    // unc的函数类型必须是(Int, Interator[T]) => Iterator[U]；
    val TupleRDD: RDD[(Int, String)] = listRDD.mapPartitionsWithIndex {
      case (num, datas) => {
        datas.map((_, "分区号：" + num))
      }
    }

    TupleRDD.collect().foreach(println);
  }
}
