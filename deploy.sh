#!/usr/bin/env bash

sbt fastOptJS fullOptJS

mkdir -p dist dist/scripts dist/styles

cp target/scala-2.11/meowsynth-*        dist/
cp target/scala-2.11/classes/index.html dist/
cp target/scala-2.11/classes/screen.css dist/
cp -r target/scala-2.11/classes/samples dist/
cp -r target/scala-2.11/classes/images  dist/

aws s3 sync dist/ s3://meowsynth.com/ --region eu-west-1 --profile untyped-dave