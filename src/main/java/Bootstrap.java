import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;

import java.util.Properties;
import java.util.stream.IntStream;

public final class Bootstrap {

    final static Logger logger = Logger.getLogger(Bootstrap.class);

    private static final String TOPIC = "topic-example-1";

    public static void main(String[] args) {

        Properties properties = new Properties();
        //properties.put("zookeeper.connect", "localhost:2181");
        properties.put("bootstrap.servers", "localhost:9091,localhost:9094,localhost:9093");
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
                logger.info("Send: " + value);
                producer.send(new ProducerRecord<>(TOPIC, value, "Message " + value));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
