Hadoop MapReduce demo
=========

Hadoop MapReduce demo with Java


    export HADOOP_CLASSPATH=$JAVA_HOME/lib/tools.jar

    cd wordcount/src/main/java

    hadoop com.sun.tools.javac.Main com/forsrc/hadoop/**/*.java

    jar cf hadoop-demo.jar com/forsrc/hadoop/**/*.class

    hadoop jar hadoop-demo.jar com/forsrc/hadoop/wordcount/WordCount input output/wordcount
    hadoop fs -cat output/wordcount/part-r-00000