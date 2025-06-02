#!/usr/bin/env sh

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Attempt to locate JAVA_HOME
if [ -z "$JAVA_HOME" ]; then
  JAVA_EXEC="java"
else
  JAVA_EXEC="$JAVA_HOME/bin/java"
fi

# Determine the directory containing this script
APP_HOME=$(cd "$(dirname "$0")" && pwd)

exec "$JAVA_EXEC" -Dorg.gradle.appname=gradlew -classpath "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
