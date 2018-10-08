#!/usr/bin/env bash
echo ================================================ START
echo `hostname` ... START

. /root/.bashrc

echo $KAFKA_HOME/bin/kafka-server-start.sh $KAFKA_HOME/conf/server.properties
$KAFKA_HOME/bin/kafka-server-start.sh $KAFKA_HOME/conf/server.properties
echo `hostname` ... END
echo ================================================ END
