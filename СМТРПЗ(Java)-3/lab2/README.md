### You need:

docker<br>
docker compose

### Run the following commands in terminal window:
```
cd lab2
docker-compose build
docker-compose up --scale eureka-client=2
```
Eureka Server URL: http://localhost:8761<br>
API-Gateway URL: http://localhost:8080<br>
Service 1 URL: http://localhost:8081<br>
Service 2 URL: http://localhost:8082<br>
