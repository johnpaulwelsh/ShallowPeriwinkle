#!/bin/sh
#Courtesy of Alvin Alexander at alvinalexander.com
THE_CLASSPATH=.
for i in `ls ./*.jar`
do
  THE_CLASSPATH=${THE_CLASSPATH}:${i}
done

# compile all the scala class files in the src directory,
# putting my jar file dependencies on the classpath, and writing
# the ".class" files as output to the current directory:
scalac -classpath "$THE_CLASSPATH" \
       ./src/*.scala
