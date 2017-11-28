#!/bin/sh
echo "Building..."
javac *.java

echo "Starting Runs..."

echo "Run1"
java Approximation_Algorithims in1.txt

echo "Run2"
java Approximation_Algorithims in2.txt

echo "Run3"
java Approximation_Algorithims in3.txt
