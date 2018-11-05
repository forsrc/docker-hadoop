package com.forsrc.spark.demo.java.demo;

import com.forsrc.utils.JarUtils;
import org.apache.spark.deploy.SparkSubmit;

import java.io.File;
import java.io.IOException;

public class WordCountSubmit {

    public static void main(String[] args) throws IOException {
        File jar = JarUtils.buildJar(WordCount.class);
        args = new String[]{
                "--master", "spark://hadoop-master:7077",
                "--name", "word count",
                "--class", WordCount.class.getName(),
                jar.toString(),
                //"spark://hadoop-master:7077",
                "local",
                "hdfs://hadoop-master:9000/user/root/input/wordcount/WordCount.txt",
                "hdfs://hadoop-master:9000/user/root/output/wordcount/"
        };
        SparkSubmit.main(args);
    }
}
