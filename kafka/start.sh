
$ZOOKEEPER_HOME/bin/zkServer.sh start

sleep 5

$KAFKA_HOME/bin/kafka-server-start.sh $KAFKA_HOME/conf/server.properties
