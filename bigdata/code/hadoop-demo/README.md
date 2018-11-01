Hadoop MapReduce demo
=========

Hadoop MapReduce demo with Java


    export HADOOP_CLASSPATH=$JAVA_HOME/lib/tools.jar

    cd hadoop-demo/src/main/java

    hadoop com.sun.tools.javac.Main com/forsrc/hadoop/**/*.java

    jar cf hadoop-demo.jar com/forsrc/hadoop/**/*.class

    hadoop jar hadoop-demo.jar com/forsrc/hadoop/wordcount/WordCount input/wordcount output/wordcount
    hadoop fs -cat output/wordcount/part-r-00000

    hadoop jar hadoop-demo.jar com/forsrc/hadoop/ncdc/NcdcTemperature input/ncdc output/ncdc
    hadoop fs -cat output/ncdc/part-r-00000