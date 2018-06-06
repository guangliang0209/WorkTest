package kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerTest {


    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "node1.hde.mht.com:6667,node2.hde.mht.com:6667,node3.hde.mht.com:6667");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        /*for (int i = 0; i < 100; i++) {
            System.out.println(i);
            producer.send(new ProducerRecord<>("UMP_MSG", Integer.toString(i)));
        }*/
        JSONObject object = new JSONObject();
        object.put("title", "title");
        object.put("content", "lalalalalala");
        object.put("users", "fengguangliang");
        object.put("sendByEmail", true);
        object.put("sendByWechat", false);
        object.put("sendBySms", true);
        producer.send(new ProducerRecord<>("UMP_MSG", object.toJSONString()));
        producer.close();
    }
}
