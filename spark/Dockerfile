FROM forsrc/hive:3.1.0

MAINTAINER forsrc <forsrc@gmail.com>

USER root


ENV SPARK_HOME=/root/spark
ENV PATH=$PATH:$SPARK_HOME/bin:$SPARK_HOME/sbin:.

ENV SPARK_NAME=spark-2.3.1

RUN wget http://archive.apache.org/dist/spark/$SPARK_NAME/$SPARK_NAME-bin-without-hadoop.tgz && \
    tar -xzvf $SPARK_NAME-bin-without-hadoop.tgz -C /root/ && \
    mv /root/$SPARK_NAME-bin-without-hadoop $SPARK_HOME && \
    rm -rf $SPARK_NAME-bin-without-hadoop.tgz


COPY config/spark/* $SPARK_HOME/conf/

CMD [ "sh", "-c", "/usr/sbin/sshd; bash"]