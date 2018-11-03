#!/usr/bin/env bash
echo ================================================ START
echo [*] `hostname` ... START

. /root/.bashrc

sleep 60

echo [*] $HIVE_HOME/bin/hive --service metastore
nohup $HIVE_HOME/bin/hive --service metastore >/dev/null 2>&1 &

echo [*] $HIVE_HOME/bin/hive --service hiveserver2
#http://127.0.0.1:10002/
nohup $HIVE_HOME/bin/hive --service hiveserver2 >/dev/null 2>&1 &

echo [*] `hostname` ... END
echo ================================================ END
