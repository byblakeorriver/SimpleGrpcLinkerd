#!/usr/bin/env sh

kubectl delete service count-server
kubectl delete deployment count-server
kubectl delete deployment count-client