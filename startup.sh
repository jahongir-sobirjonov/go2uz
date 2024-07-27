#!/bin/bash

# Run the Spring Boot application using Gradle and save logs to log.txt
nohup ./gradlew bootRun > /path/to/log.txt 2>&1 &

# Save the process ID (PID) to a file for later use
echo $! > /path/to/pid.file
