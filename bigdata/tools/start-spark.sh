#!/usr/bin/env bash
echo ================================================ START
echo `hostname` ... START

. /root/.bashrc

echo $SPARK_HOME/sbin/start-all.sh
$SPARK_HOME/sbin/start-all.sh

echo $SPARK_HOME/sbin/start-history-server.sh
mkdir /tmp/spark-events
$SPARK_HOME/sbin/start-history-server.sh

echo $SPARK_HOME/bin/spark-shell
$SPARK_HOME/bin/spark-shell

echo `hostname` ... END
echo ================================================ END
