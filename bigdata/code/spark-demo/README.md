# spark demo

```
hdfs dfs -put /code/spark-demo/src/main/resources/WordCount.txt input/wordcount

spark-submit --master spark://hadoop-master:7077 --class com.forsrc.spark.demo.java.WordCount /code/spark-demo/target/spark-demo-0.0.1-SNAPSHOT.jar spark://hadoop-master:7077 hdfs://hadoop-master:9000/user/root/input/wordcount/WordCount.txt hdfs://hadoop-master:9000/user/root/output/wordcount/

hadoop fs -cat output/wordcount/part-*

```
