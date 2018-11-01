package com.forsrc.hadoop.ncdc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class NcdcTemperature {

    public static class MaxTemperatureMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String year = line.substring(15, 19);
            int temperature = line.charAt(87) == '+' ? Integer.valueOf(line.substring(88, 92)) : Integer.valueOf(line.substring(87, 92));
            String quality = line.substring(92, 93);
            boolean isValid = temperature != 9999 && quality.matches("[01459]");
            if (isValid) {
                context.write(new Text(year), new IntWritable(temperature));
            }

        }
    }

    public static class MaxTemperatureReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int max = Integer.MIN_VALUE;
            for (IntWritable value : values) {
                max = Math.max(max, value.get());
            }
            context.write(key, new IntWritable(max));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("mapreduce.input.fileinputformat.input.dir.recursive", "true");

        final Job job = Job.getInstance(conf, "ncdc temperature");
        job.setJarByClass(NcdcTemperature.class);
        job.setMapperClass(NcdcTemperature.MaxTemperatureMapper.class);
        job.setCombinerClass(NcdcTemperature.MaxTemperatureReducer.class);
        job.setReducerClass(NcdcTemperature.MaxTemperatureReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileSystem fileSystem = FileSystem.get(conf);
        Path path = new Path(args[0]);


        listStatus(fileSystem, path, new Handler<FileStatus>() {
            public void handle(FileStatus fileStatus) throws IOException {
                System.out.println(fileStatus);
            }
        });

        FileInputFormat.addInputPath(job, path);

        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    public static void listStatus(FileSystem fileSystem, Path path, Handler<FileStatus> handler) throws IOException {
        FileStatus[] fileStatuses = fileSystem.listStatus(path);
        for (FileStatus fileStatus : fileStatuses) {
            if (fileStatus.isDirectory()) {
                listStatus(fileSystem, fileStatus.getPath(), handler);
            } else {
                handler.handle(fileStatus);
            }

        }
    }


    public static interface Handler<T> {
        void handle(T t) throws IOException;
    }
}
