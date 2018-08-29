FROM forsrc/centos-jdk-ssh:1.8.0

MAINTAINER forsrc <forsrc@gmail.com>

USER root

ENV ZOOKEEPER_HOME=/root/zookeeper
ENV PATH=$PATH:.:$ZOOKEEPER_HOME/bin

ENV ZOOKEEPER_NAME=zookeeper-3.4.13

RUN wget http://archive.apache.org/dist/zookeeper/$ZOOKEEPER_NAME/$ZOOKEEPER_NAME.tar.gz && \
	tar -xzvf $ZOOKEEPER_NAME.tar.gz -C /root/ && \
    mv /root/$ZOOKEEPER_NAME $ZOOKEEPER_HOME && \
	rm -rf $ZOOKEEPER_NAME.tar.gz

ADD config/zoo.cfg $ZOOKEEPER_HOME/conf/

EXPOSE 2181

CMD ["sh", "-c", "$ZOOKEEPER_HOME/bin/zkServer.sh start; bash"]