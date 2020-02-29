package com.forsrc.spark.demo.java.demo;

import org.apache.spark.deploy.SparkSubmit;

public class PiSubmit {

    public static void main(String[] args) {
        System.setProperty("user.name", "root");
        args = new String[]{
                "--conf", "spark.dynamicAllocation.enabled=false",
                "--master", "spark://hadoop-master:7077",
                "--num-executors", "2",
                "--name", "SparkPi",
                "--class", "org.apache.spark.examples.SparkPi",
                "../../tools/spark-2.3.1-bin-hadoop2.7/examples/jars/spark-examples_2.11-2.3.1.jar",
                "100000"
        };
        SparkSubmit.main(args);
    }
}
