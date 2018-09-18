#!/usr/bin/env bash
echo `hostname` ... START

. /root/.bashrc

echo $HIVE_HOME/hive --service metastore
$HIVE_HOME/hive --service metastore

echo `hostname` ... END
