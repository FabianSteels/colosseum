#!/bin/bash
. /etc/lsb-release

CURDIR="$( cd "$( dirname "$0" )" && pwd )"
export MACHINE_MYSQL_ROOT_PASSWORD=""
export DATABASE_USERNAME=frontend
export DATABASE_PASSWORD=frontend
export DATABASE_NAME=frontend
#cat >/tmp/script <<FIN
mysql -u root --password=$MACHINE_MYSQL_ROOT_PASSWORD   <<FIN
create user ${DATABASE_USERNAME} identified by '${DATABASE_PASSWORD}';
create database ${DATABASE_NAME} character set utf8;
grant all on ${DATABASE_NAME}.* to '${DATABASE_USERNAME}'@'localhost' identified by '${DATABASE_PASSWORD}';


flush privileges;


FIN
