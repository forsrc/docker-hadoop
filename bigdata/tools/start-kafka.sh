echo `hostname` ... START

echo $ZOOKEEPER_HOME/bin/zkServer.sh start
$ZOOKEEPER_HOME/bin/zkServer.sh start

sleep 3
echo $KAFKA_HOME/bin/kafka-server-start.sh $KAFKA_HOME/conf/server.properties
$KAFKA_HOME/bin/kafka-server-start.sh $KAFKA_HOME/conf/server.properties
echo `hostname` ... END
