#!/bin/bash
# {\"include\":[{\"project\":\"foo\",\"config\":\"Debug\"},{\"project\":\"bar\",\"config\":\"Release\"}]}"

outputJSON="{\"include\":["
allthings=$(find "./src/test/java" -name "*.java" -print)
allthings=(${allthings[0]})
echo allthings = \\/
echo $allthings
for i in $allthings
  do
  echo $i
  done
echo starting json creator
for i in ${!allthings[@]}
  do
  echo i is $i
  value="${allthings[$i]}"
  echo $value
  fileName=$(basename $value)
  className=${fileName%.*}
  if [ $i -ne 0 ]; then
    outputJSON=$outputJSON","
  fi
  outputJSON=$outputJSON"{\"path\":\""
  outputJSON=$outputJSON${value}
  outputJSON=$outputJSON$"\",\"name\":\""
  outputJSON=$outputJSON${fileName}
  outputJSON=$outputJSON$"\",\"class\":\""
  outputJSON=$outputJSON${className}
  outputJSON=$outputJSON$"\"}"
  done
outputJSON=$outputJSON"]}"
echo ::set-output name=matrix::"$outputJSON"