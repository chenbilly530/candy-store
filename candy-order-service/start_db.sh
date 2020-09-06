#!/bin/bash/env bash

docker kill mysql

docker rm mysql

docker run --name mysql -e MYSQL_PASSWORD=password -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=candystore -e MYSQL_USER=candystore -p 9000:3306 -d mysql:latest