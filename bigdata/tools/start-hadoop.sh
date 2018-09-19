#!/usr/bin/env bash
echo `hostname` ... START

. /root/.bashrc

echo $HADOOP_HOME/sbin/start-dfs.sh
$HADOOP_HOME/sbin/start-dfs.sh
echo $HADOOP_HOME/sbin/start-yarn.sh
$HADOOP_HOME/sbin/start-yarn.sh

sleep 2

echo `hostname` ... END
