package com

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark09_Oper9_distinct {
  def main(args: Array[String]): Unit = {
    //local模式
    //创建SparkConf对象
    //设定Spark计算框架的运行（部署）环境
    val config = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    //创建Spark上下文对象
    val sc = new SparkContext(config)

    //生成数据，按照指定的规则进行过滤
    val listRDD: RDD[Int] = sc.makeRDD(List(1,2,1,5,2,9,6,1))
//作用：对源RDD进行去重后返回一个新的RDD。默认情况下，只有8个并行任务来操作，但是可以传入一个可选的numTasks参数改变它。
    //val distinctRDD: RDD[Int] = listRDD.distinct()
    val distinctRDD: RDD[Int] = listRDD.distinct(2)

    //distinctRDD.collect().foreach(println);
    distinctRDD.saveAsTextFile("distinctOutpt")
  }
}
