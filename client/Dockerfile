FROM adoptopenjdk/openjdk8:alpine-slim

LABEL name="client" \
      version="1.0" \
      architecture="x86_64"

RUN apk add --update --no-cache --upgrade curl bash tini libtasn1 musl curl libjpeg-turbo libpng openjdk8 jq bats protobuf-c

EXPOSE 80

WORKDIR /opt

ADD build/distributions/client.tar .

WORKDIR /opt/client

ENTRYPOINT ["/sbin/tini", "--", "bin/client"]
