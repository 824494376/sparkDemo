package com

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark07_Oper8_sample {
  def main(args: Array[String]): Unit = {
    //local模式
    //创建SparkConf对象
    //设定Spark计算框架的运行（部署）环境
    val config = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    //创建Spark上下文对象
    val sc = new SparkContext(config)

    //生成数据，按照指定的规则进行过滤
    val listRDD: RDD[Int] = sc.makeRDD(1 to 10)

    //作用：以指定的随机种子随机抽样出数量为fraction的数据，
    // withReplacement表示是抽出的数据是否放回，true为有放回的抽样，false为无放回的抽样，seed用于指定随机数生成器种子。
    val sampleRDD: RDD[Int] = listRDD.sample(false, 0, 1)

    sampleRDD.collect().foreach(println);

  }
}
