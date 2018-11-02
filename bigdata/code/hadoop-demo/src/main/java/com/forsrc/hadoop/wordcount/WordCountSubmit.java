package com.forsrc.hadoop.wordcount;

import com.forsrc.hadoop.utils.JarUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

public class WordCountSubmit {

    public static class TokenizerMapper
            extends Mapper<Object, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word, one);
            }
        }
    }

    public static class IntSumReducer
            extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable();

        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context
        ) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws Exception {

        // windows:  route -p add 172.25.0.0 MASK 255.255.255.0 10.0.75.2  ### route delete 172.25.0.0
        //System.setProperty("hadoop.home.dir", "C:\\tools\\apache\\hadoop-2.7.7");
        System.setProperty("HADOOP_USER_NAME", "root");

        Configuration conf = new Configuration();

//        conf.set("mapreduce.cluster.local.dir","/tmp/hadoop/local");
//        conf.set("hadoop.job.user", "root");
//        conf.set("mapred.remote.os","Linux");
//        //conf.set("fs.default.name", "hdfs://hadoop-master:9000");
//        conf.addResource("classpath:hadoop/core-site.xml");
//        conf.addResource("classpath:hadoop/hdfs-site.xml");
//        conf.addResource("classpath:hadoop/mapred-site.xml");
//        conf.addResource("classpath:hadoop/yarn-site.xml");
//        conf.set("fs.defaultFS", "hdfs://hadoop-master:9000");
//        conf.set("mapred.job.tracker", "hadoop-master:9001");
//        conf.set("mapreduce.framework.name", "yarn");
//        conf.set("yarn.resourcemanager.address", "hadoop-master:8032");

        conf.set("mapreduce.app-submission.cross-platform", "true");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");

        File jar = JarUtils.buildJar(WordCountSubmit.class);
        Job job = Job.getInstance(conf, "word count submit");
        job.setJar(jar.toString());
        job.setJarByClass(WordCountSubmit.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);


        if (args.length == 0) {
            args = new String[]{"input/wordcount", "output/wordcount"};
        }
        FileSystem fileSystem = FileSystem.get(conf);
        fileSystem.delete(new Path(args[1]), true);

        FileStatus[] fileStatuses = fileSystem.listStatus(new Path("/"));
        for (FileStatus fileStatus : fileStatuses) {
            System.out.println(fileStatus);
        }
        System.out.println();

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}