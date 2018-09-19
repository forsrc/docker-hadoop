#!/usr/bin/env bash
echo `hostname` ... START

. /root/.bashrc

echo $ZOOKEEPER_HOME/bin/zkServer.sh start
$ZOOKEEPER_HOME/bin/zkServer.sh start

sleep 3
$ZOOKEEPER_HOME/bin/zkServer.sh status

echo `hostname` ... END