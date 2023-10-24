<h2> How to run this application </h2>

* Running Mysql
* Open docker
    * Running RabbitMQ in terminal<br>
    `docker run --rm -it -p 5672:5672 rabbitmq:3.11.0`
* Running Zipkin <br>
    - In springboot-microservice directory run this command with terminal <br>
      `java -jar .\zipkin-server-2.24.2-exec.jar`
* Running All Service in this application
* Run other departement service if you want <br>
    * Run this command in terminal <br>
    `java -jar -Dserver.port=8082 "D:\your\folder\springboot-microservices\departement-service\target\departement-service-0.0.1-SNAPSHOT.jar"`
* See Eureka client in this url <br>
    `http://localhost:8761/`
* See Zipkin in this  url <br>
    `http://127.0.0.1:9411/`
* See Actuator in thi url <br>
    `http://localhost:8080/actuator/` <br>
* Employee Actuator to see Department service <br>
  `http://localhost:8081/actuator/health/`