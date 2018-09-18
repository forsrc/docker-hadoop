echo `hostname` ... START

echo $HADOOP_HOME/sbin/start-dfs.sh
$HADOOP_HOME/sbin/start-dfs.sh
echo $HADOOP_HOME/sbin/start-yarn.sh
$HADOOP_HOME/sbin/start-yarn.sh

echo `hostname` ... END
