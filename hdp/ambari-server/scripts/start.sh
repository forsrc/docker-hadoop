#!/bin/bash

sleep 20
ambari-server start

while true; do
  sleep 3
  tail -f /var/log/ambari-server/ambari-server.log
done
