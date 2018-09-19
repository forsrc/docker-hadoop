#!/usr/bin/env bash
echo `hostname` ... START

. /root/.bashrc

echo $HIVE_HOME/bin/hive --service metastore
$HIVE_HOME/bin/hive --service metastore

echo `hostname` ... END
