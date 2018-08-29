```
docker-compose up -d

docker-compose exec spark-master hdfs namenode -format

docker-compose exec spark-master schematool -dbType mysql -initSchema

# docker-compose exec spark-master jar cv0f /code/spark-libs.jar -C /root/spark/jars/ .
# docker-compose exec spark-master hadoop fs -mkdir -p /user/spark/share/lib/
# docker-compose exec spark-master hadoop fs -put /code/spark-libs.jar /user/spark/share/lib/

############################################################

docker-compose exec spark-master start-dfs.sh
docker-compose exec spark-master start-yarn.sh
docker-compose exec spark-master /root/spark/sbin/start-all.sh

docker-compose exec spark-master /root/spark/sbin/stop-all.sh
docker-compose exec spark-master stop-yarn.sh
docker-compose exec spark-master stop-dfs.sh

```