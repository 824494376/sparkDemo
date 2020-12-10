package com

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

object Spark17_shareData {

  def main(args: Array[String]): Unit = {
    //local模式
    //创建SparkConf对象
    //设定Spark计算框架的运行（部署）环境
    val config = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    //创建Spark上下文对象
    val sc = new SparkContext(config)

    val dataRDD: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)
    //var sum=0;
    //dataRDD.foreach(i=>{sum=sum+i})
    //创建累加器对象
    val accumulator: LongAccumulator = sc.longAccumulator
    dataRDD.foreach{
      case i => {
        //执行累加器的累加功能
        accumulator.add(i)
      }
    }
    println("sum:"+accumulator.value)
  }

}
