# Project Setup and Deployment

This project consists of a Spring Boot backend (using Cassandra as a database) and an Angular frontend, orchestrated with Docker.

## Requirements

To build and run the project, ensure the following tools and dependencies are installed on your system:

1. **Java Development Kit (JDK)**  
   - **Version**: 21 or higher  
   - **Purpose**: Required for building the Spring Boot backend.

2. **Maven**  
   - **Version**: 3.6 or higher  
   - **Purpose**: Used for building the Spring Boot application.

3. **Node.js and npm**  
   - **Node.js Version**: 14 or higher (recommended)  
   - **npm Version**: 6 or higher (installed with Node.js)  
   - **Purpose**: Required for building the Angular frontend.

4. **Docker**  
   - **Version**: 20.10 or higher  
   - **Purpose**: Needed to run the application using containers.

## Building and running the project
You can run the project by running `./run.sh` from the root directory. This will:
  1. Build the spring boot app using maven
  2. Build the angular app using npm
  3. Start Cassandra database container
  4. Initialiaze it with some sample data using `/init-cassandra/init.cql`
  5. Start Spring boot backend and angular front containers
Note: If you have already built the spring boot app and the angular app you can run the containers directly using: `docker-compose` in the root directory
If the docker container start succesfully:
  - the spring boot backend should be available on: `http://localhost:8080`
  - the angular frontend should be available on: `http://localhost:4200`


### Authentication
  The backend API is secured and requires authorization via a JWT token. To get this token you need to first authorize yourself via the login endpoint: 
  ###  **Login**  
- **URL:** `api/auth/login`  
- **Method:** `POST`  
- **Request Body:**  
  ```json
  {
    "username": "example_user",
    "password": "example_password"
  }

 Response:
   - Code: 200 OK
   - Body: The JWT token.
   - Example:
      ```
        {
          "token": "generated_token"
        }


After succsefully authorizing yourself you can access the secured endpoints by adding the `Authorization` header:
    
    Authorization: Bearer <token>


### Creating new users
Although this is not implemented in the Angular frontend app the backend API also provides an endpoind for creating new users:
###  **Register**  
- **URL:** `api/auth/register`  
- **Method:** `POST`  
- **Request Body:**  
  ```json
  {
    "username": "example_user",
    "password": "example_password"
  }

 Response:
   - Code: 200 OK
   - Body: The JWT token.
   - Example:
      ```
        {
          "token": "generated_token"
        }

This will create a new user and provide the token for authorizing similar to the login endpoint.


### Testing users
For testing purposes the app provides an example user you can use to test the application:

    username: admin
    password: 1234


## Available Product endpoints
Note: all products requests require authorization via JWT token


### 1. **Create Product**  
- **URL:** `/products`  
- **Method:** `POST`  
- **Request Body:**  
  ```json
  {
    "name": "Product Name",
    "description": "Product Description",
    "price": 19.99,
    "category": "Category Name"
  }

    Response:
        Code: 200 OK
        Body: The created product object.
        Example:

        {
          "id": "1",
          "name": "Product Name",
          "description": "Product Description",
          "price": 19.99,
          "category": "Category Name"
        }

 ### 2. **Get Product by ID**  

  - **URL:** `/products/{id}`
  - **Method:** `GET`
  - **Parameters:**
      - **id:** The ID of the product to retrieve.
   - **Response:**
        ```Code: 200 OK
        Body: The product object with the given ID.
        Example:

        {
          "id": "1",
          "name": "Product Name",
          "description": "Product Description",
          "price": 19.99,
          "category": "Category Name"
        }

### 3. **Get All Products**

  - **URL:** `/products`
  - **Method:** `GET`
  - **Response:**
    - Code: 200 OK
    - Body: A list of all product objects.
    - Example:

        ```
        [
          {
            "id": "1",
            "name": "Product Name 1",
            "description": "Product Description 1",
            "price": 19.99,
            "category": "Category Name"
          },
          {
            "id": "2",
            "name": "Product Name 2",
            "description": "Product Description 2",
            "price": 29.99,
            "category": "Category Name"
          }
        ]

### 4. **Update Product**

  - **URL:** `/products/{id}`
  - **Method:** `PUT`
  - **Parameters:** 
    - **id:** The ID of the product to update.
  - **Request Body:**
```{
  "name": "Updated Product Name",
  "description": "Updated Product Description",
  "price": 29.99,
  "category": "Updated Category Name"
}
```
Response:

- Code: 200 OK
- Body: The updated product object.
- Example:

        {
          "id": "1",
          "name": "Updated Product Name",
          "description": "Updated Product Description",
          "price": 29.99,
          "category": "Updated Category Name"
        }

### 5. **Delete Product**

- **URL:** `/products/{id}`
- **Method:** `DELETE`
- Parameters:
  - **id:** The ID of the product to delete.
- **Response:**
  - Code: 200 OK
  - Body: Empty response (successful deletion).

### 6. **Get Products by Category**

- **URL:** `/products/category/{category}`
- **Method:** `GET`
- **Parameters:**
  - **category:** The category to filter products by.
- Response:
  - Code: 200 OK
  - Body: A list of products within the given category.
  - Example:

        [
          {
            "id": "1",
            "name": "Product Name",
            "description": "Product Description",
            "price": 19.99,
            "category": "Category Name"
          }
        ]
