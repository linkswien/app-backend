#!/bin/sh

if [ $1 = develop ]
then
   echo "Deploying changes to DEV environment..."
   export COMPOSE_PROJECT_NAME=app-backend-dev
   export APP_BACKEND_PORT=5001
   export OAUTH_CALLBACKURI=https://dev.backend.app.links-wien.at/api/v1/login/callback
else
   echo "Deploying changes to PROD environment..."
   export COMPOSE_PROJECT_NAME=app-backend
   export APP_BACKEND_PORT=5000
   export OAUTH_CALLBACKURI=https://backend.app.links-wien.at/api/v1/login/callback
fi

./gradlew bootJar

docker build . -t app-backend

docker-compose up -d

echo "Deployed!"