#!/bin/sh
# Maven Wrapper script for Unix/Mac
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.
# ----------------------------------------------------------------------------

set -euo pipefail

MAVEN_PROJECTBASEDIR="${MAVEN_BASEDIR:-$(cd "$(dirname "$0")" && pwd)}"
MAVEN_WRAPPER_JAR="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar"
MAVEN_WRAPPER_PROPERTIES="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.properties"

if [ -r "$MAVEN_WRAPPER_PROPERTIES" ]; then
    MAVEN_URL=$(grep "distributionUrl" "$MAVEN_WRAPPER_PROPERTIES" | cut -d'=' -f2-)
fi

MAVEN_HOME="$HOME/.m2/wrapper/dists/apache-maven-3.9.6"

if [ ! -d "$MAVEN_HOME" ]; then
    echo "Downloading Maven 3.9.6..."
    mkdir -p "$MAVEN_HOME"
    curl -fsSL "$MAVEN_URL" -o "$MAVEN_HOME/maven.zip"
    unzip -qo "$MAVEN_HOME/maven.zip" -d "$MAVEN_HOME"
    rm "$MAVEN_HOME/maven.zip"
fi

MAVEN_BIN=$(find "$MAVEN_HOME" -name "mvn" -type f | head -1)
exec "$MAVEN_BIN" "$@"
