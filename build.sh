#!/usr/bin/env bash

sbt fastOptJS fullOptJS

mkdir -p dist

rsync --archive --delete target/scala-2.11/meowsynth-*        dist/
rsync --archive --delete target/scala-2.11/classes/index.html dist/
rsync --archive --delete target/scala-2.11/classes/screen.css dist/
rsync --archive --delete target/scala-2.11/classes/samples    dist/
rsync --archive --delete target/scala-2.11/classes/images     dist/
