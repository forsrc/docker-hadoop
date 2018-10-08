#!/usr/bin/env bash
echo ================================================ START
echo `hostname` ... START

. /root/.bashrc

echo $SPARK_HOME/sbin/start-all.sh
$SPARK_HOME/sbin/start-all.sh

echo `hostname` ... END
echo ================================================ END
