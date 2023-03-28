$Deployment = az spring app deployment list \
  -g pmacho-rg \
  -s pmacho-asc \
  --app credit-service \
  --query "[?properties.active == \`false\`].name" \
  -o tsv

az spring app deployment delete \
  -g pmacho-rg \
  -s pmacho-asc \
  --app credit-service \
  -n $Deployment
