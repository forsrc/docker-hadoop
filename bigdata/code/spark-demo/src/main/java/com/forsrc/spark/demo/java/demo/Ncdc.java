package com.forsrc.spark.demo.java.demo;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

public class Ncdc {
    public static void main(String[] args) {
        String master = args.length > 0 ? args[0] : "local";
        String input = args.length > 0 ? args[1] : "hdfs://hadoop-master:9000/user/root/input/ncdc/*/*.gz";
        String output = args.length > 0 ? args[2] : "hdfs://hadoop-master:9000/user/root/output/ncdc/";
        SparkConf sparkConf = new SparkConf()
                .setAppName("forsrc-spark-ncdc")
                //.setMaster(master)
                //.setMaster("spark://hadoop-master:7077")
                ;
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> dataRdd = javaSparkContext.textFile(input).cache();

        JavaPairRDD<String, Integer> yearTemperaturePairRdd = dataRdd.mapToPair(new PairFunction<String, String, Integer>() {

            @Override
            public Tuple2<String, Integer> call(String value) throws Exception {
                String line = value;
                String year = line.substring(15, 19);
                int temperature = line.charAt(87) == '+' ? Integer.valueOf(line.substring(88, 92)) : Integer.valueOf(line.substring(87, 92));
                String quality = line.substring(92, 93);
                boolean isValid = temperature != 9999 && quality.matches("[01459]");
                if (isValid) {
                    return new Tuple2(year, temperature);
                }
                return new Tuple2(year, Integer.MIN_VALUE);
            }
        });

        JavaPairRDD<String, Integer> maxTemperaturePairRdd = yearTemperaturePairRdd
                .reduceByKey(new Function2<Integer, Integer, Integer>() {

                    @Override
                    public Integer call(Integer x, Integer y) throws Exception {
                        if (x.equals(Integer.MIN_VALUE)) {
                            return y;
                        }
                        if (y.equals(Integer.MIN_VALUE)) {
                            return x;
                        }
                        return Math.max(x, y);
                    }
                });

        JavaPairRDD<String, Integer> minTemperaturePairRdd = yearTemperaturePairRdd
                .reduceByKey(new Function2<Integer, Integer, Integer>() {

                    @Override
                    public Integer call(Integer x, Integer y) throws Exception {
                        if (x.equals(Integer.MIN_VALUE)) {
                            return y;
                        }
                        if (y.equals(Integer.MIN_VALUE)) {
                            return x;
                        }
                        return Math.min(x, y);
                    }
                });
        maxTemperaturePairRdd.foreach(line -> System.out.println("max -> " + line));
        minTemperaturePairRdd.foreach(line -> System.out.println("min -> " + line));
        javaSparkContext.stop();
    }
}
