package com

import java.util

import org.apache.spark.rdd.RDD
import org.apache.spark.util.{AccumulatorV2, LongAccumulator}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 自定义累加器
 */
object Spartk17_Accumulator {

  def main(args: Array[String]): Unit = {
    //local模式
    //创建SparkConf对象
    //设定Spark计算框架的运行（部署）环境
    val config = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    //创建Spark上下文对象
    val sc = new SparkContext(config)

    val dataRDD=sc.makeRDD(List("hadoop","hive","hbase","Scala","Spark"))

    //创建累加器
    val myAccumulator=new MyAccumulator()
    //注册累加器
    sc.register(myAccumulator)
    dataRDD.foreach{
      case word=>{
        myAccumulator.add(word)
      }
    }
    println(myAccumulator.value)
  }

}

/**
 * 自定义累加器
 */
//[输入值，输出值]
class MyAccumulator extends AccumulatorV2[String,util.ArrayList[String]]{
  val list=new util.ArrayList[String]()
  //当前累加器是否为初始化状态
  override def isZero: Boolean = {
    list.isEmpty
  }

  //复制累加器对象
  override def copy(): AccumulatorV2[String, util.ArrayList[String]] = {
    new MyAccumulator()
  }

  //重置累加器对象
  override def reset(): Unit = {
    list.clear()
  }

  //向累加器增加对象
  override def add(v: String): Unit ={
    if(v.contains("h")){
      list.add(v)
    }
  }

  //合并
  override def merge(other: AccumulatorV2[String, util.ArrayList[String]]): Unit = {
    list.addAll(other.value)
  }

  //返回累加器的值
  override def value: util.ArrayList[String] = {
    list
  }
}
