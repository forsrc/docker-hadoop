```

docker-compose exec  hadoop-master hadoop namenode -format

docker-compose exec  hadoop-master start-dfs.sh
docker-compose exec  hadoop-master start-yarn.sh
docker-compose exec  hadoop-master /root/spark/sbin/start-all.sh

docker-compose exec  hadoop-master bash
$ /root/hbase/bin/start-hbase.sh
$ /root/hbase/bin/hbase shell

docker-compose exec  hadoop-master /root/spark/sbin/stop-all.sh
docker-compose exec  hadoop-master stop-yarn.sh
docker-compose exec  hadoop-master stop-dfs.sh



#####################################################################


# /root/hbase/bin/hbase-daemon.sh start master
# /root/hbase/bin/hbase-daemon.sh start regionserver

#docker-compose exec  hadoop-master /root/hbase/bin/hbase org.apache.hadoop.hbase.util.hbck.OfflineMetaRepair

#docker-compose exec  hadoop-master /root/hbase/bin/hbase hbck -fixVersionFile

#docker-compose exec  zoo1 /root/zookeeper/bin/zkServer.sh status




cp /usr/share/zoneinfo/UTC /etc/localtime
cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
yum install ntpdate
ntpdate  0.cn.pool.ntp.org 
```