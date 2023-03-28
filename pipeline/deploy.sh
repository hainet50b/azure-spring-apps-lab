az spring app deployment create \
  -n default-$(date "+%Y%m%d%H%M%S") \
  -g pmacho-rg \
  -s pmacho-asc \
  --app credit-service \
  --artifact-path target/credit-service.jar
