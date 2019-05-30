# Commands

## Producer
/opt/bitnami/kafka/bin/kafka-console-producer.sh --broker-list kafka-1:9092,kafka-2:9092,kafka-3:9092  --topic topic-example-1

## Consumer
/opt/bitnami/kafka/bin/kafka-console-consumer.sh --zookeeper zookeeper:2181 --topic topic-example-1 --from-beginning