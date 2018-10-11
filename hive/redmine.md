```
# ALTER USER  'root'@'%'         IDENTIFIED WITH mysql_native_password BY 'root';
# GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'root' WITH GRANT OPTION; #mysql5.7
# FLUSH PRIVILEGES;


hadoop fs -mkdir /tmp 
hadoop fs -mkdir /user/hive/warehouse
hadoop fs -chmod g+w /tmp 
hadoop fs -chmod g+w /user/hive/warehouse

schematool -dbType mysql -initSchema


hive


hive --service metastore

hive --service hwi #127.0.0.1:9999/hwi

hiveserver2



beeline
beeline> !connect jdbc:hive2://hadoop-master:10000

```