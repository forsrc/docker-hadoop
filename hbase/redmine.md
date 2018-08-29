```

docker-compose exec  hbase-master start-dfs.sh
docker-compose exec  hbase-master start-yarn.sh
docker-compose exec  hbase-master /root/spark/sbin/start-all.sh


docker-compose exec  hbase-master /root/hbase/bin/hbase-daemon.sh start master


docker-compose exec  hbase-master /root/spark/sbin/stop-all.sh
docker-compose exec  hbase-master stop-yarn.sh
docker-compose exec  hbase-master stop-dfs.sh
```