package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerTest {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "node1.hde.mht.com:6667,node2.hde.mht.com:6667,node3.hde.mht.com:6667");
        props.put("group.id", "test11");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("UMP_MSG"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            System.out.println(records);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record);
                System.out.printf(record.value());
            }
        }
    }

}