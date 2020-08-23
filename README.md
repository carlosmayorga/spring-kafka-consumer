# spring-kafka-consumer
Spring Kafka consumer of topic 

### This is the consumer of the producer : https://github.com/carlosmayorga/spring-kafka-producer

To see the Sequence diagram of this solution, go to https://bramp.github.io/js-sequence-diagrams/ and paste the code below:
```
Microservice Producer->Kafka Broker: Send message to topic

Note right of Kafka Broker: Handle in other Thread

Kafka Broker->>Microservice Producer: Response 200 ok 

Kafka Broker->Microservice Consumer: Send the message 

Microservice Consumer->H2: Save or Update
```



