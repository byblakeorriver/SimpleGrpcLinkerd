#!/usr/bin/env sh

DOCKER_REPO="byblakeorriver"
CLIENT_VERSION="1.0.3"
SERVER_VERSION="1.0.3"

./gradlew clean build
docker build -t "${DOCKER_REPO}/countserver:${SERVER_VERSION}" server/
docker build -t "${DOCKER_REPO}/countclient:${CLIENT_VERSION}" client/
docker push "${DOCKER_REPO}/countserver:${SERVER_VERSION}"
docker push "${DOCKER_REPO}/countclient:${CLIENT_VERSION}"

kubectl apply -f server/service.yaml
kubectl apply -f server/deployment.yaml
sleep 60s # this is just so the server will be up when it starts
kubectl apply -f client/deployment.yaml
