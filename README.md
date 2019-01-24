# flight-search
API to provide flight search for both business and cheap. Supports sort, filter and paging. Code is written in Spring-Boot

Features
--
- Search all available flights, or search by selected provider (`cheap` or `business`)
- Filter results by `departure`, `arrival`
- Sort results by `departure`, `arrival`,`departureTime`, `arrivalTime`
- Show only one result page, with page size is configurable


Environment
--
- Framework: SpringBoot
- JDK version: 1.8
- Build tool: maven

Configuration
--
- available flight’s providers URL can be found at `/src/main/resources/application.properties`
- running port number is configurable, current value is `8080`

How to build
--
- `mvn clean install -DskipTests` to skip tests
- `mvn clean install` will build and execute all 5 test cases (internet connection is required)

How to run
--
- `mvn spring-boot:run`
- access [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html/) using web browser
to test the API from SwaggerUI

How to test
--
- This code is also deployed to 
[https://available-flight.herokuapp.com/swagger-ui.html](https://available-flight.herokuapp.com/swagger-ui.html)

![Alt text](screenshot.png?raw=true "Available Flight API screenshot")

