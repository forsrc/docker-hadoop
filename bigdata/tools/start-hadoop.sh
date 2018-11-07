#!/usr/bin/env bash
echo ================================================ START
echo [*] `hostname` ... START

. /root/.bashrc

echo [*] $HADOOP_HOME/sbin/start-dfs.sh
$HADOOP_HOME/sbin/start-dfs.sh
echo [*] $HADOOP_HOME/sbin/start-yarn.sh
$HADOOP_HOME/sbin/start-yarn.sh

# hadoop-2.7.7
#echo [*] hadoop-daemon.sh start datanode
#hadoop-daemon.sh start datanode
#echo [*] $HADOOP_HOME/sbin/mr-jobhistory-daemon.sh start historyserver
#$HADOOP_HOME/sbin/mr-jobhistory-daemon.sh start historyserver

# hadoop-3.1.1
echo [*] hdfs --daemon start datanode
hdfs --daemon start datanode
echo [*] mapred --daemon start historyserver
mapred --daemon start historyserver
sleep 2

echo [*] `hostname` ... END
echo ================================================ END
