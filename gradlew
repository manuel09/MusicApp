#!/bin/sh
# Gradle wrapper script for Unix/Linux
DIR="$(cd "$(dirname "$0")" && pwd)"
exec "$DIR/gradle/wrapper/gradle-wrapper.jar" "$@"