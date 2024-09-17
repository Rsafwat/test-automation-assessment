#!/bin/bash

# Step 1: Save the current directory
PROJECT_DIR=$(pwd)

# Step 2: Start running tests with docker-compose
echo "Starting run tests with docker-compose..."
docker-compose -f test-suites.yaml up  
if [ $? -ne 0 ]; then
  echo "Test execution failed. Exiting."
  exit 1
fi

echo "Test execution completed successfully!"

# Step 3: Return to the original project directory
cd "$PROJECT_DIR"
