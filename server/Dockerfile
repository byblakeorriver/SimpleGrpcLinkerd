FROM adoptopenjdk/openjdk8:alpine-slim

LABEL name="server" \
      version="1.0" \
      architecture="x86_64"

RUN apk add --update --no-cache --upgrade curl bash tini libtasn1 musl curl libjpeg-turbo libpng openjdk8 jq bats protobuf-c

EXPOSE 80

WORKDIR /opt

ADD build/distributions/server.tar .

WORKDIR /opt/server

ENTRYPOINT ["/sbin/tini", "--", "bin/server"]
