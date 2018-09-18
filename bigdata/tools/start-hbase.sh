#!/usr/bin/env bash
echo `hostname` ... START

. /root/.bashrc

echo /usr/sbin/sshd
/usr/sbin/sshd

echo `hostname` ... END
