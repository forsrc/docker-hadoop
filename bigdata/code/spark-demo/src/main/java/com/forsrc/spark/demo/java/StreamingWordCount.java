package com.forsrc.spark.demo.java;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StreamingWordCount {

    public static void main(String[] args) {

        String zk = "localhost:2181";
        String topic = "topic-spark-streaming-wordcount";

        SparkConf sparkConf = new SparkConf()
                .setAppName("forsrc-spark-streaming-wordcount")
                .setMaster("local[4]");

        JavaStreamingContext javaStreamingContext = new JavaStreamingContext(sparkConf, Durations.seconds(10));

        javaStreamingContext.checkpoint("/tmp/spark/streaming/checkpoint");

        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "localhost:9092");
        //kafkaParams.put("key.serializer", StringSerializer.class);
        //kafkaParams.put("value.serializer", StringSerializer.class);
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "group-spark-streaming-test");
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);

        Collection<String> topics = Arrays.asList(topic);

        JavaInputDStream<ConsumerRecord<String, String>> stream =
                KafkaUtils.createDirectStream(
                        javaStreamingContext,
                        LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams)
                );

        JavaPairDStream<String, String> records = stream.mapToPair(record -> new Tuple2<>(record.key(), record.value()));

        JavaDStream<String> words = records.flatMap(t -> Arrays.asList(t._2.split(" ")).iterator());

        JavaPairDStream<String, Integer> pairs = words.mapToPair(s -> new Tuple2<>(s, 1));
        //JavaPairDStream<String, Integer> wordCounts = pairs.reduceByKey((i1, i2) -> i1 + i2);


//        JavaPairDStream<String, Integer> wordCounts = pairs.updateStateByKey(new Function2<List<Integer>, org.apache.spark.api.java.Optional<Integer>, org.apache.spark.api.java.Optional<Integer>>() {
//            @Override
//            public org.apache.spark.api.java.Optional<Integer> call(List<Integer> values, org.apache.spark.api.java.Optional<Integer> state) throws Exception {
//                Integer updatedValue = 0;
//
//                if (state.isPresent()) {
//                    updatedValue = state.get();
//                }
//                for (Integer value : values) {
//                    updatedValue += value;
//                }
//                return org.apache.spark.api.java.Optional.of(updatedValue);
//            }
//        });

        JavaPairDStream<String, Integer> wordCounts = pairs.updateStateByKey((values, state) -> {
            Integer updatedValue = 0;
            if (state.isPresent()) {
                updatedValue = state.get();
            }
            for (Integer value : values) {
                updatedValue += value;
            }
            return org.apache.spark.api.java.Optional.of(updatedValue);
        });

        wordCounts.print();
        javaStreamingContext.start();              // Start the computation
        try {
            javaStreamingContext.awaitTermination();   // Wait for the computation to terminate
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            javaStreamingContext.close();
        }

    }


}