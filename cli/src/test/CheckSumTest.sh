#!/bin/bash

PICOCLIJAR="/Users/yxu/.m2/repository/info/picocli/picocli/4.6.1/picocli-4.6.1.jar"
CLIJAR="../../target/cli-1.0-SNAPSHOT.jar"

cd ../../
mvn package

cd src/test/
echo "hello" > hello.txt
java -cp "${PICOCLIJAR}:${CLIJAR}" cli.CheckSum -a SHA-256 hello.txt
