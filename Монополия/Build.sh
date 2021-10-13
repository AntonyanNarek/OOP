#!/bin/bash

javac -sourcepath ./src -classpath ./lib/logback-core-1.2.6.jar:./lib/logback-classic-1.2.6.jar:./lib/slf4j-api-1.7.32.jar -d bin ./src/edu/csf/oop/monopoly/Main.java

jar --create --file monopoly.jar --manifest .src/META-INF/MANIFEST.mf -C ./bin/ .
