#!/bin/bash

set -e

GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m' # No color

echo -e "${GREEN}Building the Spring Boot application...${NC}"
cd demo-product-backend
mvn clean package -DskipTests

if [ $? -eq 0 ]; then
  echo -e "${GREEN}Spring Boot application built successfully!${NC}"
else
  echo -e "${RED}Spring Boot build failed. Exiting...${NC}"
  exit 1
fi
cd ..

echo -e "${GREEN}Building the Angular application...${NC}"
cd product-demo-front
npm install
npm run build --prod

if [ $? -eq 0 ]; then
  echo -e "${GREEN}Angular application built successfully!${NC}"
else
  echo -e "${RED}Angular build failed. Exiting...${NC}"
  exit 1
fi
cd ..

echo -e "${GREEN}Starting Docker containers...${NC}"
docker-compose down
docker-compose up --build -d

if [ $? -eq 0 ]; then
  echo -e "${GREEN}Containers started successfully!${NC}"
  echo -e "${GREEN}Application URLs:${NC}"
  echo -e "  - Spring Boot backend: ${GREEN}http://localhost:8080${NC}"
  echo -e "  - Angular frontend: ${GREEN}http://localhost:4200${NC}"
else
  echo -e "${RED}Failed to start containers. Exiting...${NC}"
  exit 1
fi
