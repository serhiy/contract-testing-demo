# Contract Testing Demo

## Disclaimer

Any of the materials used in this repository are for educational purpose only. Before using any of the materials, please make sure to read the best practices for the respective frameworks and libaries. The code in this repository should not to be used as it is in any externally exposed development environment due to the security risks present in this codebase. Author cannot be held liable for any damage caused by the code in this repository, use at your own discresion.    

## Requirements

- Docker
- Java 17 (`JAVA_HOME` must be pointing to Java 17 version, otherwise add the following argument to all gradle commands `-Dorg.gradle.java.home=JAVA_17_HOME`)

## Instructions

### Start Pact Broker

Start Pact Broker on localhost:9292

```
./docker compose up
```

### Consumer (orders- or suggestions-service)

#### Run tests and generate contracts

```
./gradlew build
```

# Publish contracts

Requires Pact Broker running on localhost:9292

```
./gradlew pactPublish
```

### Provider (products-service)

#### Get contracts from Pact Broker and run tests

```
./gradlew test
```
