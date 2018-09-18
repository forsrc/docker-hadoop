FROM ubuntu:18.10

MAINTAINER forsrc <forsrc@gmail.com>

USER root

RUN apt-get update && \
    apt-get install -y vim openssh-server openssh-client

RUN sed -ri 's/^#PermitRootLogin yes/PermitRootLogin yes/' /etc/ssh/sshd_config
RUN sed -ri 's/^UsePAM yes/UsePAM no/' /etc/ssh/sshd_config

#generate key files
#RUN ssh-keygen -q -t rsa   -b 2048 -f /etc/ssh/ssh_host_rsa_key      -N ''
#RUN ssh-keygen -q -t ecdsa         -f /etc/ssh/ssh_host_ecdsa_key    -N ''
#RUN ssh-keygen -q -t dsa           -f /etc/ssh/ssh_host_ed25519_key  -N ''

# login localhost without password
RUN ssh-keygen -f /root/.ssh/id_rsa -N ''
RUN touch /root/.ssh/authorized_keys
RUN cat /root/.ssh/id_rsa.pub >> /root/.ssh/authorized_keys

RUN mkdir /var/run/sshd
RUN echo "\nStrictHostKeyChecking no\n" >> /etc/ssh/ssh_config
RUN echo "\nUserKnownHostsFile /dev/null\n" >> /etc/ssh/ssh_config


# set password of root
RUN echo "root:root" | chpasswd

# open the port 22
EXPOSE 22

ADD start.sh /

CMD ["/bin/sh", "-c", "/start.sh; bash"]
#ENTRYPOINT ["/bin/sh", "-c", "/start.sh; bash"]
