#!/bin/bash
RESOURCE_GROUP="pmacho-rg"
SERVICE_NAME="pmacho-asa"
APPLICATION_NAME="credit-service"

az spring app deployment create \
  -n default-$(date "+%Y%m%d%H%M%S") \
  -g $RESOURCE_GROUP \
  -s $SERVICE_NAME \
  --app $APPLICATION_NAME \
  --artifact-path target/$APPLICATION_NAME.jar
