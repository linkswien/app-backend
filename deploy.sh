#!/bin/sh

if [ $1 = dev ]
then
   echo "Deploying changes to DEV environment..."
   export COMPOSE_PROJECT_NAME=app-backend-dev
   export APP_BACKEND_PORT=5001
   git checkout develop
else
   echo "Deploying changes to PROD environment..."
   export COMPOSE_PROJECT_NAME=app-backend
   export APP_BACKEND_PORT=5000
   git checkout main
fi

git pull

./gradlew bootJar

docker build . -t app-backend

docker-compose up -d

echo "Deployed!"