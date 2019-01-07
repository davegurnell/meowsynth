#!/usr/bin/env bash

if [ -z "$(git status --porcelain)" ]; then
  # Working directory clean
  git subtree split --prefix docs -b gh-pages
  git push -f origin gh-pages:gh-pages
  git branch -D gh-pages
else
  # Uncommitted changes
  echo 'ERROR! Uncommitted changes in the working copy.'
  echo 'Please commit all outstanding changes and run again to deploy from `/docs`.'
fi
