echo `hostname` ... START

echo $HIVE_HOME/hive --service metastore
$HIVE_HOME/hive --service metastore

echo `hostname` ... END
