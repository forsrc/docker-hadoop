#!/usr/bin/env bash
echo [*] `hostname` ... START

# ALTER USER  'root'@'%'         IDENTIFIED WITH mysql_native_password BY 'root';
# GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'root' WITH GRANT OPTION; #mysql5.7
# FLUSH PRIVILEGES;


hadoop fs -mkdir /tmp
hadoop fs -mkdir /user
hadoop fs -mkdir /user/hive
hadoop fs -mkdir /user/hive/warehouse
hadoop fs -chmod g+w /tmp
hadoop fs -chmod g+w /user/hive/warehouse

schematool -dbType mysql -initSchema

echo [*] `hostname` ... END
