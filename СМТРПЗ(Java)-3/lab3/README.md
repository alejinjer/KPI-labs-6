### You need:

docker<br>
docker compose

## Run project:
```
cd lab3/config-server/configdir
git init
git add -A
git commit -m "..."

cd lab3
docker-compose build
docker-compose up --scale eureka-client=2
```

To test config server, run the following commands when all microservices started-up<br>
and make requests from test.http.
```
docker container ls                                // find config-server instance id
docker exec -it [config-server instance id] bin/sh // connect to container
cd configdir                                       // here you can change any config file
```

Eureka Server URL: http://localhost:8761<br>
API-Gateway URL: http://localhost:8080<br>
Service 1 URL: http://localhost:8081<br>
Service 2 URL: http://localhost:8082<br>
Config Server URL: http://localhost:8888<br>
