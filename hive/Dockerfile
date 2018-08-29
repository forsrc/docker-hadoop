FROM forsrc/hadoop:2.7.7

MAINTAINER forsrc <forsrc@gmail.com>

USER root

ENV HIVE_HOME=/root/hive

ENV PATH=$PATH:$HIVE_HOME/bin:.

ENV HIVE_NAME=hive-3.1.0

RUN wget http://archive.apache.org/dist/hive/$HIVE_NAME/apache-$HIVE_NAME-bin.tar.gz && \
    tar -xzvf apache-$HIVE_NAME-bin.tar.gz -C /root/ && \
    mv /root/apache-$HIVE_NAME-bin $HIVE_HOME && \
    rm -rf apache-$HIVE_NAME-bin.tar.gz

RUN wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-5.1.40.tar.gz && \
    tar -xzvf mysql-connector-java-5.1.40.tar.gz -C /root/ && \
    mv /root/mysql-connector-java-5.1.40/mysql-connector-java-5.1.40-bin.jar $HIVE_HOME/lib/ && \
    rm -rf /root/mysql-connector-java-5.1.40 && \
    rm -rf /root/mysql-connector-java-5.1.40.tar.gz

COPY config/* $HIVE_HOME/conf/

CMD ["/bin/bash"]