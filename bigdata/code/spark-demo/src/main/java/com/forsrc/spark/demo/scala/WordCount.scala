package com.forsrc.spark.demo.scala;
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object WordCount {

    def main(args: Array[String]): Unit = {
        val inputFile = WordCount.getClass.getClassLoader.getResource("WordCount.txt").getFile 
        val conf = new SparkConf().setAppName("WordCount").setMaster("local")
        val sc = new SparkContext(conf)
        val textFile = sc.textFile(inputFile)
        val wordCount = textFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey((a, b) => a + b)
        wordCount.foreach(println)
        sc.stop();
    }
}
