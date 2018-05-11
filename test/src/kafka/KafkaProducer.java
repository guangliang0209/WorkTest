package kafka;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

public class KafkaProducer extends Thread {
    private String topic;

    public KafkaProducer(String topic) {
        super();
        this.topic = topic;
    }

    public static void main(String[] args) {
        new KafkaProducer("mytopic").start();
    }

    @Override
    public void run() {
        Producer producer = createProducer();
        int i = 0;
        while (true) {
            producer.send(new KeyedMessage<Integer, String>(topic, "message:" + i++));
            System.out.println("发送成功！");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Producer createProducer() {
        Properties properties = new Properties();
        properties.put("zk.connect", "localhost:2181");
        properties.put("serializer.class", StringEncoder.class.getName());
        properties.put("metadata.broker.list", "localhost:9092");
        return new Producer<Integer, String>(new ProducerConfig(properties));
    }
}
