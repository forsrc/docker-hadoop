#!/usr/bin/env bash
echo ================================================ START
echo `hostname` ... START

. /root/.bashrc

echo $HBASE_HOME/bin/start-hbase.sh
$HBASE_HOME/bin/start-hbase.sh

echo `hostname` ... END
echo ================================================ END
