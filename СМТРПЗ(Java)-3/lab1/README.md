### DB uses the one only table "users"
```
CREATE DATABASE lab1;
use lab1;
CREATE TABLE users(ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 NAME VARCHAR(255), SURNAME VARCHAR(255), EMAIL VARCHAR(255),
 GENDER VARCHAR(255), COUNTRY VARCHAR(255);
```
### To run Eureka service and client with Maven, run the following commands in terminal window:
```
cd lab1
./mvnw spring-boot:run -pl eureka-service
./mvnw spring-boot:run -pl eureka-client
```
Eureka Server URL: http://localhost:8761<br>
Service URL: http://localhost:8081
