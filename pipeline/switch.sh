$Deployment = az spring app deployment list \
  -g pmacho-rg \
  -s pmacho-asc \
  --app credit-service \
  --query "[?properties.active == \`false\`].name" \
  -o tsv

az spring app set-deployment \
  -g pmacho-rg \
  -s pmacho-asc \
  -n credit-service \
  -d $Deployment
