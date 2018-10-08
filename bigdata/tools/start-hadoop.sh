#!/usr/bin/env bash
echo ================================================ START
echo `hostname` ... START

. /root/.bashrc

echo $HADOOP_HOME/sbin/start-dfs.sh
$HADOOP_HOME/sbin/start-dfs.sh
echo $HADOOP_HOME/sbin/start-yarn.sh
$HADOOP_HOME/sbin/start-yarn.sh
echo $HADOOP_HOME/sbin/mr-jobhistory-daemon.sh start historyserver
$HADOOP_HOME/sbin/mr-jobhistory-daemon.sh start historyserver
sleep 2

echo `hostname` ... END
echo ================================================ END
