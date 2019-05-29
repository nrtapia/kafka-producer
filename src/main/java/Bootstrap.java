import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.stream.IntStream;

public final class Bootstrap {

    private static final String TOPIC = "topic-example-1";

    public static void main(String[] args) {

        Properties properties = new Properties();
        //properties.put("zookeeper.connect", "localhost:2181");
        properties.put("bootstrap.servers", "PLAINTEXT://172.20.0.5:9092");
        //properties.put("acks", "1");
        //properties.put("retries", 2);
        //properties.put("batch.size", 16384);
        //properties.put("linger.ms", 1);
        //properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {
            IntStream.rangeClosed(1, 10).forEach(i -> {
                String value = String.valueOf(i);
                System.out.println("Send: " + value);
                producer.send(new ProducerRecord<>(TOPIC, value, "Message " + value));
                System.out.println("End " + value);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
