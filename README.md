# Phone number lookup

## Overview
This is a simple phone number lookup REST service built on Spring Boot.

### Maven

This project uses maven as build tool. To build and run the project you can simply use
standard maven commands.

```
$ mvn spring-boot:run 
```
When service is running you can access it under 8080 port (http://localhost:8080)
The root path serves the simple web page which talks with REST endpoints.

To run tests
```
$ mvn test 
```

### Spring Profiles

This application can be run using Spring profiles (dev | production).

The "dev" profile will run with DEBUG logging level.
The "production" profile will run with WARN logging level.

```
$ -Dspring.profiles.active=dev|production
```


