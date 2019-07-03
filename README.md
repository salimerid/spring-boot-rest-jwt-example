# Spring Boot REST Application using JPA + Hibernate + MySQL + JWT

### What's inside

   - Spring Boot
   - Maven
   - MySQL
   - JPA
   - Hibernate
   - JWT
   
## Installation

The project is created with Maven, so you just need to import it to your IDE and build the project to resolve the dependencies.

#### Database configuration

Create a MySQL database with the name `stories_db` and add the credentials to /resources/application.properties.
The default ones are :

     spring.datasource.url = jdbc:mysql://localhost:3306/stories_db?createDatabaseIfNotExist=true&useSSL=false
     spring.datasource.username = root
     spring.datasource.password = 1234
     spring.jpa.hibernate.ddl-auto=update
     
#### Usage

  Run the project through the IDE and head out to http://localhost:8080
    
  or
    
  Go on the project's root folder, run this command in the command line:
  
     $ mvn spring-boot:run

### Prerequisites

   - Java 8
   - Maven 3
   - MySQL

### Once Application will be started successfully

We can call following endpoints by using POSTMAN

##### We used Spring Boot Security Jwt (Token Based) Authentication

##### => To user registration on the system use following URL with `POST` Request

    http://localhost:8080/api/user
    
   - set content type as in header as `application/json`
   - set request body as raw with JSON payload
  
          {
            "userName": "admin",
            "password" : "password",
            "roles" : "ADMIN"
          }
  
##### => To login the system use following URL with `POST` Request

    http://localhost:8080/api/auth/login
    
   - set request body as raw with JSON payload 
  
         {
            "username": "admin",
            "password" : "password"
         }
         
   response:
     
        {
             "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInNjb3BlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9BRE1JTiJ9XSwiaXNzIjoiYWRtaW4iLCJpYXQiOjE1MzQxNzMxMzUsImV4cCI6MTUzNDE5MTEzNX0.........."
        }
    
##### => To Create New Story use following URL with `POST` Request
    http://localhost:8080/api/story
    
   - set `Authorization` as in header as `Bearer   <token>`
   - set content type as in header as `application/json`
   - set request body as raw with JSON payload
   
          {
            "title": "New Story",
            "body" : "New Story Body",
            "author" :"Salim Ahmed"
          }
          
##### => To read the stories in json format use following URL with `GET` Request
    http://localhost:8080/api/stories?page=0&limit=5
   - set content type as in header as `application/json` to read json format stories.
   
##### => To update a story in json format use following URL with `PUT` Request
    http://localhost:8080/api/story/{id}
    
   - set `Authorization` as in header as `Bearer   <token>`
   - set content type as in header as `application/json` to update json format story.
   - set request body as raw with payload
   
           {
             "title": "Update Story",
             "body" : "Update story body",
             "author" :"Salim Ahmed"
           }
   
##### => To delete a story use following URL with `DELETE` Request

     http://localhost:8080/api/story/{id}
       
   - set `Authorization` as in header as `Bearer   <token>`
 
 
##### => To search the stories use following URL with `GET` Request
 
     http://localhost:8080/api/story?search=title:test,body:test,author:test
        
   - set `Authorization` as in header as `Bearer   <token>`
   - set content type as in header as `application/json`
 
##### => Consume this endpoints with React JS
This endpoints was consume the [react-js-axios-example](https://github.com/salimerid/react-js-axios-example) .

