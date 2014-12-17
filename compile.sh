#!/bin/sh
#Courtesy of Alvin Alexander at alvinalexander.com
# Remove all .class files from this directory before we compile
rm *.class

THE_CLASSPATH=.
for i in `ls *.jar`
do
  THE_CLASSPATH=${THE_CLASSPATH}:${i}
done
# compile all the scala class files in the src directory,
# putting my jar file dependencies on the classpath, and writing
# the ".class" files as output to the current directory:
scalac -classpath "$THE_CLASSPATH" ./src/*.scala
echo "Done compiling"

# run the Runner class with the first and second bash parameters and the same classpath
scala -classpath "$THE_CLASSPATH" Runner $1 $2
