package demo

import org.apache.spark.{SparkConf, SparkContext}

object Demo01 {
  def main(args: Array[String]): Unit = {
    val config = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    //创建Spark上下文对象
    val sc = new SparkContext(config)
    val listRDD=sc.makeRDD(List(1,2,3,4))



    println(listRDD.mapPartitionsWithIndex( //两个参数，必须()
      (index, datas) => {
        datas.map {
          data => { //因为函数只有一行代码，所以此{}可以省略
            (index, data)
          }
          //data=>(index, data)
        }
      }
    ).collect().mkString("  "))

    println(listRDD.mapPartitionsWithIndex(
      (index, datas) => {
        datas.map {
          data => (index, data)  //因为data只出现一次，所以可以替换成_
        }
      }
    ).collect().mkString("  "))

    println(listRDD.mapPartitionsWithIndex(
      (index, datas) => {
        datas.map {(index,_)} //因为只有一行代码，所以可以用()
      }
    ).collect().mkString("  "))

    println(listRDD.mapPartitionsWithIndex(
      (index, datas) => {//只有一行代码，可以用()
        datas.map((index,_))
      }
    ).collect().mkString("  "))

    println(listRDD.mapPartitionsWithIndex(
      (index, datas) => (
        datas.map((index,_))
        )  //**单行用()可以运行，但是不推荐**
    ).collect().mkString("  "))

  }
}
