#!/bin/bash

# Step 1: Start Selenium Grid and  using docker-compose
echo "Starting Selenium Grid with docker-compose..."
docker-compose -f grid.yaml up  -d
if [ $? -ne 0 ]; then
  echo "Failed to start Selenium Grid. Exiting."
  exit 1
fi

echo "Selenium Grid is up and running!"
# Step 2: Return to the original project directory
