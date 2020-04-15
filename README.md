#Simple gRPC Client and Server

##Requirements
kubectl

docker

A docker repository

A kubernetes cluster running with the ability to run `kubectl apply`

A linkerd-controller and linkerd-proxy injector running. 

Linux or Mac OS

##Configurations

This is very thrown together. 

You will need to change the DOCKER_REPO variable in the runme.sh to your
docker repo. 

In `client/deployment.yaml` you will need to change the docker repo accordingly
to match your docker repo.

The same will need to happen in `server/deployment.yaml`.

To disable linkerd change `spec.template.metadata.annotations.linkerd.io/inject` to `disabled`
in `client/deployment.yaml`.

##Running

To run: `./runme.sh`
To uninstall from kubernetes: `./uninstall.sh`