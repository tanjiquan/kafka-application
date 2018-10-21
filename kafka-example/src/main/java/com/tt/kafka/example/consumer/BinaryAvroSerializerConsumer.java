package com.tt.kafka.example.consumer;

import com.tt.kafka.example.avro.AvroMessageData;
import com.tt.kafka.example.constant.KafkaParam;
import com.twitter.bijection.Injection;
import com.twitter.bijection.avro.SpecificAvroCodecs;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import scala.util.Try;

import java.util.Arrays;
import java.util.Properties;


/**
 * avro 序列化方式 消费端, 不使用Schema Registry。
 *
 * 从byte[]中还原Avro对象，依赖com.twitter:bijection-avro_${sacla_version}
 *
 * @author tanjiquan [tan_jiquan@163.com]
 * @date 2018/10/18 11:13
 * @since 1.0
 */
public class BinaryAvroSerializerConsumer {
    private final KafkaConsumer<String, byte[]> consumer;
    private final String brokerList;
    private final String inputTopic;
    Injection<AvroMessageData, byte[]> specificBinaryInjection = SpecificAvroCodecs.toBinary(AvroMessageData.SCHEMA$);

    public static void main(String[] args) {

        String inputTopic = KafkaParam.STRING_TOPIC;
        String brokerList = KafkaParam.BROKER_LIST;

        BinaryAvroSerializerConsumer clickConsumer = new BinaryAvroSerializerConsumer(brokerList, inputTopic);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> clickConsumer.consumer.close()));
        clickConsumer.run();
    }

    public BinaryAvroSerializerConsumer(String brokerList, String inputTopic) {
        this.inputTopic = inputTopic;
        this.brokerList = brokerList;
        this.consumer = getConsumer();
    }


    private void run() {
        KafkaConsumer<String, byte[]> consumer = getConsumer();
        consumer.subscribe(Arrays.asList(this.inputTopic));
        while (true) {
            ConsumerRecords<String, byte[]> records = consumer.poll(2000);
            System.out.println("poll returned " + records.count() + " records");
            for (ConsumerRecord<String, byte[]> record : records) {
                System.out.println(record.offset() + "@" + record.partition());
                String ip = record.key();
                byte[] eventBytes = record.value();
                Try<AvroMessageData> eventTry = specificBinaryInjection.invert(eventBytes);
                if (eventTry.isFailure()) {
                    System.out.println("failed to apply record");
                } else {
                    AvroMessageData messageData = eventTry.get();
                    System.out.println("ip:" + ip);
                    System.out.println("data:" + messageData);
                }
            }
        }
    }

    private KafkaConsumer<String, byte[]> getConsumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", brokerList);
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");//不自动提交offset
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset", "latest");//配合不自动提交offset使用，使得consumer从最早的数据开始读取
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        return new KafkaConsumer<>(props);
    }

}