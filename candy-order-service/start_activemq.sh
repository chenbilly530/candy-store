#!/bin/bash/env bash

docker kill activemq

docker rm activemq

docker run -it --rm   -e ARTEMIS_USERNAME=zchen  -e ARTEMIS_PASSWORD=password -p 8161:8161 -p 61616:61616 vromero/activemq-artemis