package kafka;

/**
 * Created by Administrator on 2018/5/11.
 */
public class KafkaConsumerProducerDemo {

    public static void main(String[] args)
    {
        KafkaProducer producerThread = new KafkaProducer("test");
        producerThread.start();

        KafkaConsumer consumerThread = new KafkaConsumer("test");
        consumerThread.start();
    }
}
