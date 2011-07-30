#!/bin/bash
CLASSPATH=./lib/craftbukkit-0.0.1-SNAPSHOT.jar:./lib/snakeyaml-1.8.jar

rm -rf bin
mkdir bin
javac -classpath $CLASSPATH -d bin src/com/ayan4m1/multiarrow/*.java src/com/ayan4m1/multiarrow/arrows/*.java

cd bin
cp ../plugin.yml .
jar cf ../MultiArrow.jar ./*
