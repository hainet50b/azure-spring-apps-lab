#!/bin/bash
RESOURCE_GROUP="pmacho-rg"
SERVICE_NAME="pmacho-asa"
APPLICATION_NAME="credit-service"

DEPLOYMENT=$(az spring app deployment list \
  -g $RESOURCE_GROUP \
  -s $SERVICE_NAME \
  --app $APPLICATION_NAME \
  --query "[?properties.active == \`false\`].name" \
  -o tsv \
)

az spring app deployment delete \
  -g $RESOURCE_GROUP \
  -s $SERVICE_NAME \
  --app $APPLICATION_NAME \
  -n $DEPLOYMENT