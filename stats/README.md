# Stats service
Provides endpoints to gather statistics about the machines in the ecosystem.

## Getting started

### Prerequisites
JDK 11, Maven

### Running locally
This application needs to connect to a MongoDB, which can be installed and running in a docker image.

### Build and run

#### Maven
```
mvn package
mvn spring-boot:run
```

### Testing
This application uses JUnit for unit testing and Cucumber for integration testing.
You can launch the tests by using the following maven command:
```
mvn test
```

### Built with
* Java 11
* SpringBoot
* SpringData Jdbc
* Swagger
* MapStruct
* H2 database
* JUnit
* Mockito
* Cucumber

## Author
**Daniel Queiroz** - [Github](https://github.com/danielqueiroz)

