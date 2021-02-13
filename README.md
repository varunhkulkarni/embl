<br />
<p align="center">
  <a href="https://github.com/varunhkulkarni/embl">  
  </a>

  <h3 align="center">EMBL Project : Person API + Eureka Server + Zuul gateway + Zuul Ratelimiter + Docker Configuration </h3>

  <p align="center">
    Docker project to host the person api using the Eureka servers and Zuul Gateway 
    <br />
    <a href="https://github.com/varunhkulkarni/embl/blob/main/README.md"><strong>Explore the docs »</strong></a>
    <br />
    <br />
  </p>
</p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#run-application-on-docker-with-pre-tagged-images">Run application on docker with pre-tagged images</a></li>
        <li><a href="#build-and-run-locally">Build and Run locally</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#docker-images">Docker Images</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

This is a project that will demonstrate the functionality for exposing a restful web-service using : Spring Rest with Netflix Eureka Services and Zuul API gateway.

Below features are implemented in this project :

* To expose the Persons API using the Spring restful API - Person Controller has the following CRUD operations : GET, GETALL, SAVE, UPDATE, DELETE
* To enable the documentation using the Swagger framework
* To enable the security for the API using spring security 
* To expose the person-service using the Zuul API
* To use the Eureka Server as load balancers to add new person-service instances to scale up or scale down
* To dockerize the whole project into one container

### Following things are considered while implementing the Service :

1. <strong>Restful web services</strong> -> Spring BOOT with Spring REST and Spring JPA. In memory database : H2
2. <strong>Testing</strong> -> Junit
      <p>Unit testing for the person Service API is implemented using the JUNIT and SpringTestSuit ( FileName : EmblApplicationTests.java)</p>
3. <strong>Security</strong> -> Spring  Security
      <p>Spring Secuity is used for securing the API Gateway using basic authentication. For demo purpose 2 users are pre-configured while starting the server ( FileName :                    SpringSecurityConfig.java) </p>
4. <strong>Scalibity</strong> -> Docker for scaling instances + Eureka Servers for load balancing
      <p>
          The number of instances of person service can be increased by running the command <strong>"docker-compose --scale embl-person-service=3"</strong> manually. Eureka service will internally have the responsbility to load balance between the instances. In real world a thirrd party tools like AWS Auto Scaling/ Kubernetes is being used to scale up or down based on the various parameters.
      </p>
5. <strong>Limitation</strong> -> Netflix Zuul rate limiter [ 429 ( Too many request) response after 10 request per minute ]
    <p> Zuul rate limiter is used to limit the number of api calls using the below configs(<strong>10 requests per minute</strong>) :
    </p>
    
    ```sh
            zuul.ratelimit.policy-list.embl-person-service[0].limit=10
            zuul.ratelimit.policy-list.embl-person-service[0].refresh-interval=60
            zuul.ratelimit.policy-list.embl-person-service[0].type[0].type=url
            zuul.ratelimit.policy-list.embl-person-service[0].type[0].matcher=/v1/persons
      ```
6. <strong>Documentation</strong> -> Swagger
    <p>
      The Swagger is being used for API documentation and configured at the API gateway level (FileName - DocumentationController.java)
   </p>
7. <strong>Deployment</strong> -> Docker
    <p>
      For deployment docker-compose is being used which will pull the pre tagged images from the repository(<strong>varunhkulkarni/embl:<tag></strong>) and start all the images  and run in a single container using the command <strong>docker-compose up &</strong>
        
  </p>

### Built With

* [JAVA 8]
* [Spring Boot/Rest/JPA]
* [Netflix Eureka]
* [Netflix Zuul - API Gateway + Rate limiter]
* [Docker]

<!-- GETTING STARTED -->
## Getting Started

This section will help to configure and run the project using the docker contatiners and also to run locally 

### Prerequisites

Following softwares should be installed on the computer :

1. JAVA 8 
2. MAVEN
3. DOCKER

### Run application on docker with pre-tagged images

To run the project on docker containers :

1. Clone the file : <b>docker-compose.yml</b> on your local machine : 

 <a href="https://github.com/varunhkulkarni/embl/tree/main/docker-images">https://github.com/varunhkulkarni/embl/tree/main/docker-images</a>
  
2. Execute the command from the location where the <b>docker-compose.yml</b> is cloned on local computer  : 

 ```sh
   docker-compose up &
   ```
   
3. Access the application once its up on the below URL : 

Swagger URL : 
  ```sh
  http://localhost:8081/api/swagger-ui.html#/API_for_Persons
  ```
  
Eureka Server URL : 

  ```sh
  http://localhost:8761/
  ```
  
4. Below default users are pre-configured to login:

   * User 1 - Admin- Full Access of the services methods: 
     ```sh
        User Name : admin
        Password  : password
       ```
   *  User 2 - User- limited Access of the services methods ( GET / GETALL): 
       ```sh
        User Name : user
        Password  : password
       ```
5. Person service APIs URLs to access using Postman tool:

      * GETALL ( Permitted User/s: user and admin )

          http://localhost:8081/api/v1/persons

      * GETBYID ( Permitted User/s: user and admin )

          http://localhost:8081/api/v1/persons/3

      * POST ( Permitted User/s: admin )
          http://localhost:8081/api/v1/persons

        Request Body: 

        ```sh
          {
            "age": 0,
            "favourite_colour": "string",
            "first_name": "string",
            "last_name": "string"
        } 
        ```


      * PUT ( Permitted User/s: admin )
             http://localhost:8081/api/v1/persons/3

        Request Body: 
           ```sh
        {
          "id": 3,
          "first_name": "Harry1",
          "last_name": "Potter1",
          "age": 15,
          "favourite_colour": "white"
        }
        ```

      * DELETE ( Permitted User/s: admin )

         http://localhost:8081/api/v1/persons/1


### Build and Run locally

Follow the below steps to build and run locally the project locally

1. Clone the repo
  ```sh
   git clone https://github.com/varunhkulkarni/embl.git
   ```

2. Go to the individual projects and run the maven clean install command from command prompt

 ```sh
  mvn clean install
  ```
  
  If you are running docker for the first time then :
  
  ```sh
  Open the docker setting 
  Go to -> General tab
  Select : Expose daemon on tcp://localhost:2375 without TLS
  ```
    
3. Check if the docker services are running fine. 

4. Run all the images together from project docker-images using docker-compse
  
  To start the project into docker :

  ```sh
  docker-compose up &
  ```
  
  To stop the project :
  
  ```sh
  docker-compose down
  ```
5. If you want to scale up the embl-person-service to 3 nodes

 ```sh
  docker-compose up --scale embl-person-service=3
  ```
  
 <!-- USAGE -->
## Usage

This section will help to query different URLs to use the API that we have started with the above code

1. URL for Eureka Server : 

  ```sh
  http://localhost:8761/
  ```
  
2. To Open the Person controller using Zuul and Swagger

  ```sh
  http://localhost:8081/api/swagger-ui.html#/API_for_Persons
  ```
<!-- Docker Images -->

3. Below default users are pre-configured to login:

  * User 1 - Admin- Full Access of the services methods: 
     ```sh
        User Name : admin
        Password  : password
       ```
   *  User 2 - User- limited Access of the services methods ( GET / GETALL): 
       ```sh
        User Name : user
        Password  : password
       ```
## Docker Images

Docker images for this application can be found in the docker hub repository on the below link :

  ```sh
  https://hub.docker.com/r/varunhkulkarni/embl
  ```


## Contact

Varun Kulkarni - varunhkulkarni24061990@gmail.com

Project Link: [https://github.com/varunhkulkarni/embl](https://github.com/varunhkulkarni/embl)


<img src="images/personapi.png" alt="API" width="500" height="300">




  
