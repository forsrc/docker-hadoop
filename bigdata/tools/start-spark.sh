echo `hostname` ... START

echo $HADOOP_HOME/sbin/start-dfs.sh
$HADOOP_HOME/sbin/start-dfs.sh
echo $HADOOP_HOME/sbin/start-yarn.sh
$HADOOP_HOME/sbin/start-yarn.sh

sleep 3
echo $SPARK_HOME/sbin/start-all.sh
$SPARK_HOME/sbin/start-all.sh

echo `hostname` ... END
