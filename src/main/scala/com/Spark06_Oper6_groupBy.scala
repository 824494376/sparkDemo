package com

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark06_Oper6_groupBy {
  def main(args: Array[String]): Unit = {
    //local模式
    //创建SparkConf对象
    //设定Spark计算框架的运行（部署）环境
    val config = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    //创建Spark上下文对象
    val sc = new SparkContext(config)

    //val listRDD: RDD[Int] = sc.makeRDD(1 to 10)
    val listRDD: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))

    //作用：分组，按照传入函数的 返回值 进行分组。将相同的key对应的值放入一个迭代器。
    //分组后的数据形成了对偶元组（k-v）,k表示分组的key,v表示分组的数据集合
    val groupByRDD: RDD[(Int, Iterable[Int])] = listRDD.groupBy(i => i % 2)

    groupByRDD.collect().foreach(println)

  }
}
