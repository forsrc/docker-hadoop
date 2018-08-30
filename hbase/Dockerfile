FROM forsrc/spark:2.3.1

MAINTAINER forsrc <forsrc@gmail.com>

USER root

ENV HBASE_HOME=/root/hbase
ENV PATH=$PATH:.:$HBASE_HOME/bin

ENV HBASE_VERSION=2.1.0

RUN wget http://archive.apache.org/dist/hbase/$HBASE_VERSION/hbase-$HBASE_VERSION-bin.tar.gz && \
	tar -xzvf hbase-$HBASE_VERSION-bin.tar.gz -C /root/ && \
    mv /root/hbase-$HBASE_VERSION $HBASE_HOME && \
	rm -rf hbase-$HBASE_VERSION-bin.tar.gz


ADD config/hbase/* $HBASE_HOME/conf/

ADD config/hadoop/* $HADOOP_CONF_DIR/

COPY config/spark/* $SPARK_HOME/conf/

CMD [ "sh", "-c", "/usr/sbin/sshd; bash"]