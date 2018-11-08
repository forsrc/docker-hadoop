#!/usr/bin/env bash
echo ================================================ START
echo [*] `hostname` ... START

. /root/.bashrc

# hadoop-2.7.7
#echo [*] $HADOOP_HOME/sbin/start-dfs.sh
#$HADOOP_HOME/sbin/start-dfs.sh

#echo [*] $HADOOP_HOME/sbin/start-yarn.sh
#$HADOOP_HOME/sbin/start-yarn.sh

#echo [*] hadoop-daemon.sh start datanode
#hadoop-daemon.sh start datanode

#echo [*] $HADOOP_HOME/sbin/mr-jobhistory-daemon.sh start historyserver
#$HADOOP_HOME/sbin/mr-jobhistory-daemon.sh start historyserver


# hadoop-3.1.1


echo [*] $HADOOP_HOME/bin/hdfs namenode -format
echo n | $HADOOP_HOME/bin/hdfs namenode -format


echo [*] $HADOOP_HOME/bin/hdfs --daemon start namenode
$HADOOP_HOME/bin/hdfs --daemon start namenode

echo [*] $HADOOP_HOME/bin/hdfs --daemon start datanode
$HADOOP_HOME/bin/hdfs --daemon start datanode

echo [*] $HADOOP_HOME/sbin/start-dfs.sh
$HADOOP_HOME/sbin/start-dfs.sh

echo [*] $HADOOP_HOME/bin/yarn --daemon start resourcemanager
$HADOOP_HOME/bin/yarn --daemon start resourcemanager

echo [*] $HADOOP_HOME/bin/yarn --daemon start nodemanager
$HADOOP_HOME/bin/yarn --daemon start nodemanager

echo [*] $HADOOP_HOME/bin/yarn --daemon start proxyserver
$HADOOP_HOME/bin/yarn --daemon start proxyserver

echo [*] $HADOOP_HOME/sbin/start-yarn.sh
$HADOOP_HOME/sbin/start-yarn.sh

echo [*] $HADOOP_HOME/bin/mapred --daemon start historyserver
$HADOOP_HOME/bin/mapred --daemon start historyserver
sleep 2

echo [*] `hostname` ... END
echo ================================================ END
