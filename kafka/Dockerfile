FROM forsrc/zookeeper:3.4.13

MAINTAINER forsrc <forsrc@gmail.com>

USER root

ENV KAFKA_HOME=/root/kafka
ENV ZOOKEEPER_HOME=/root/zookeeper
ENV PATH=$PATH:.:$ZOOKEEPER_HOME/bin:$KAFKA_HOME/bin

ENV KAFKA_NAME=kafka_2.12-2.0.0

RUN wget http://archive.apache.org/dist/kafka/2.0.0/$KAFKA_NAME.tgz && \
	tar -xzvf $KAFKA_NAME.tgz -C /root/ && \
    mv /root/$KAFKA_NAME $KAFKA_HOME && \
	rm -rf $KAFKA_NAME.tgz

ADD config/zoo.cfg $ZOOKEEPER_HOME/conf/
ADD config/* $KAFKA_HOME/conf/
ADD start.sh /

EXPOSE 2181
EXPOSE 9092


CMD ["sh", "-c", "start.sh; bash"]