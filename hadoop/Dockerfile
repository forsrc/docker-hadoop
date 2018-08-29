FROM forsrc/centos-jdk-ssh:1.8.0

MAINTAINER forsrc <forsrc@gmail.com>

USER root

ENV HADOOP_HOME=/root/hadoop
ENV HADOOP_CONF_DIR=$HADOOP_HOME/etc/hadoop
ENV LD_LIBRARY_PATH=$LD_LIBRARY_PATH:$HADOOP_HOME/lib/native/:/root/protobuf/lib
ENV PATH=$PATH:/root/protobuf/bin:$HADOOP_HOME/bin:$HADOOP_HOME/sbin:.
ENV HADOOP_NAME=hadoop-2.7.7

RUN ln -s /usr/local/java/jdk-1.8.0 /usr/local/java/jdk

#RUN yum install -y initscripts openssh openssh-server openssh-clients openssl openssl-devel bzip2-devel && \
#    yum clean all && \
#    ssh-keygen -t rsa -f ~/.ssh/id_rsa -P '' && \
#    cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys

RUN yum install -y protobuf which exec


ADD config/conf/ssh_config /root/.ssh/config
RUN chmod 600 /root/.ssh/config && \
    chown root:root /root/.ssh/config

#ADD $HADOOP_NAME.tar.gz ./

RUN echo $HADOOP_NAME && \
    wget http://archive.apache.org/dist/hadoop/core/$HADOOP_NAME/$HADOOP_NAME.tar.gz && \
	tar -xzvf $HADOOP_NAME.tar.gz -C /root/ && \
    mv /root/$HADOOP_NAME $HADOOP_HOME && \
    rm -rf $HADOOP_NAME.tar.gz

ADD config/conf/bashrc /root/.bashrc

ADD config/hadoop/* $HADOOP_HOME/etc/hadoop/

#RUN mkdir -p /works/hadoop/dfs/name && \
#    mkdir -p /works/hadoop/dfs/data && \
#    mkdir -p /works/hadoop/dfs/namesecondary && \
#    mkdir $HADOOP_HOME/logs && \
#    $HADOOP_HOME/bin/hdfs namenode -format

CMD [ "sh", "-c", "/usr/sbin/sshd; bash"]
