#!/usr/bin/env bash
echo ================================================ START
echo [*] `hostname` ... START

. /root/.bashrc

echo [*] $SPARK_HOME/sbin/start-all.sh
$SPARK_HOME/sbin/start-all.sh

# echo [*] $SPARK_HOME/bin/spark-class org.apache.spark.deploy.worker.Worker spark://hadoop-master:7077
# $SPARK_HOME/bin/spark-class org.apache.spark.deploy.worker.Worker spark://hadoop-master:7077

echo [*] $SPARK_HOME/sbin/start-history-server.sh
mkdir /tmp/spark-events
$SPARK_HOME/sbin/start-history-server.sh

sleep 10
echo [*] $SPARK_HOME/bin/spark-shell --master spark://hadoop-master:7077 --total-executor-cores 2
$SPARK_HOME/bin/spark-shell --master spark://hadoop-master:7077 --total-executor-cores 2

echo [*] `hostname` ... END
echo ================================================ END
