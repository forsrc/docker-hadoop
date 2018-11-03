package com.forsrc.spark.demo.java;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

public class WordCount {

    public static void main(String[] args) {
        String filename = WordCount.class.getClassLoader().getResource("WordCount.txt").getFile();

        SparkConf sparkConf = new SparkConf().setAppName("forsrc-spark-wordcount").setMaster("local");
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);
        
        //JavaSparkContext javaSparkContext = new JavaSparkContext(
                //"local", "wordcount", System.getenv("SPARK_HOME"), System.getenv("JARS"));

        JavaRDD<String> dataRdd = javaSparkContext.textFile(filename);

        JavaRDD<String> wordsRdd = dataRdd.flatMap(new FlatMapFunction<String, String>() {

            @Override
            public Iterator<String> call(String line) throws Exception {
                return Arrays.asList(line.split(" ")).iterator();
            }
        });
        JavaPairRDD<String, Integer> wordCountPairRdd = wordsRdd.mapToPair(new PairFunction<String, String, Integer>() {

            @Override
            public Tuple2<String, Integer> call(String word) throws Exception {
                return new Tuple2(word, 1);
            }
        });
        JavaPairRDD<String, Integer> countPairRdd = wordCountPairRdd
                .reduceByKey(new Function2<Integer, Integer, Integer>() {

                    @Override
                    public Integer call(Integer x, Integer y) throws Exception {
                        return x + y;
                    }
                });
        countPairRdd.foreach(new VoidFunction<Tuple2<String, Integer>>() {

            @Override
            public void call(Tuple2<String, Integer> t) throws Exception {
                System.out.println(t);

            }
        });
        // countPairRdd.saveAsTextFile("WordCount");
        javaSparkContext.close();
    }
}