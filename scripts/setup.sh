#!/bin/bash

# Step 0: Remove the target directory (if exists)
echo "Removing target directory..."
sudo rm -rf target
if [ $? -ne 0 ]; then
  echo "Failed to remove target directory. Exiting."
  exit 1
fi

# Step 1: Clean and package the Maven project
echo "Running Maven clean package..."
mvn clean package -DskipTests
if [ $? -ne 0 ]; then
  echo "Maven build failed. Exiting."
  exit 1
fi

# Step 2: Build the Docker image
echo "Building Docker image..."
docker build -t docker/selenium-image .
if [ $? -ne 0 ]; then
  echo "Docker image build failed. Exiting."
  exit 1
fi

echo "Docker image built successfully!"
