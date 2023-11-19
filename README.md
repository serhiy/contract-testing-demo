# Start Pact Broker

docker compose up

# Run Tests (will generate pacts on consumer)

gradlew test

# Publish Pact

gradlew pactPublish