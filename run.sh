#!/bin/bash
mkdir -p bin

javac -d bin src/*.java

if [ $? -eq 0 ]; then
    echo "Compilé"
    java -cp bin Game
else
    echo "Erreur de compilation"
fi