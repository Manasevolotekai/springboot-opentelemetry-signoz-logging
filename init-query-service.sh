#!/bin/sh

echo "[INIT] Starting init-query-service.sh"

if [ "$INIT_MYSQL" = "true" ]; then
  echo "[INIT] INIT_MYSQL is true - running migrations"
  mysql -h mysql_sipstr -u root -proot signoz < /schema.sql
fi


exec /root/query-service -config=/root/config.yaml
