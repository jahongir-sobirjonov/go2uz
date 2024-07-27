#!/bin/bash

# Define paths for the log file and PID file
LOG_FILE="/home/youruser/app/log.txt"
PID_FILE="/home/youruser/app/pid.file"

# Ensure the directory for the log file exists
mkdir -p "$(dirname "$LOG_FILE")"

# Run the Spring Boot application using Gradle and save logs to the specified log file
nohup ./gradlew bootRun > "$LOG_FILE" 2>&1 &

# Save the process ID (PID) to a file for later use
echo $! > "$PID_FILE"
