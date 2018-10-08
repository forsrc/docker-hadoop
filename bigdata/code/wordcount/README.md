wordcount
=========

Hadoop MapReduce word counting with Java

Run with:

    hadoop jar wordcount.jar "input_folder" "output_folder"

"input_folder" and "output_folder" are folders on HDFS.


OR:

    export HADOOP_CLASSPATH=$JAVA_HOME/lib/tools.jar

    cd wordcount/src/main/java
    hadoop com.sun.tools.javac.Main com/forsrc/hadoop/WordCount.java
    jar cf WordCount.jar com/forsrc/hadoop/WordCount*.class

    hadoop jar WordCount.jar com/forsrc/hadoop/WordCount input output/wordcount
    hadoop fs -cat output/wordcount/part-r-00000