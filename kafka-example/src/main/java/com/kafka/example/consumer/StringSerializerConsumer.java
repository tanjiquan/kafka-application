package com.kafka.example.consumer;

import com.kafka.example.constant.KafkaParam;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * StringSerializer 类型消费端
 *
 * @author tanjiquan [tan_jiquan@163.com]
 * @date 2018/10/18 11:51
 * @since 1.0
 */
public class StringSerializerConsumer {

    private final KafkaConsumer<String, String> consumer;
    private final String brokerList;
    private final String inputTopic;

    public static void main(String[] args) {

        String inputTopic = KafkaParam.STRING_TOPIC;
        String brokerList = KafkaParam.BROKER_LIST;

        StringSerializerConsumer clickConsumer = new StringSerializerConsumer(brokerList, inputTopic);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> clickConsumer.consumer.close()));
        clickConsumer.run();
    }

    public StringSerializerConsumer(String brokerList, String inputTopic) {
        this.inputTopic = inputTopic;
        this.brokerList = brokerList;
        this.consumer = getConsumer();
    }


    private void run() {
        KafkaConsumer<String, String> consumer = getConsumer();
        consumer.subscribe(Arrays.asList(this.inputTopic));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(10000);
            System.out.println("poll returned " + records.count() + " records");
            for (ConsumerRecord<String, String> record : records) {
                String key = record.key();
                String value = record.value();
                System.out.println(record.offset() + " @ " + record.partition());
                System.out.println("receive messageData: " + value);
            }
        }
    }

    private KafkaConsumer<String, String> getConsumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", brokerList);
        props.put("group.id", "test");
        //不自动提交offset
        props.put("enable.auto.commit", "false");
        props.put("session.timeout.ms", "30000");
        props.put("client.id", "mac");
        //配合不自动提交offset使用，使得consumer从最早的数据开始读取
        props.put("auto.offset.reset", "latest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("key.deserializer.encoding", "UTF8");
        props.put("value.deserializer.encoding", "UTF8");
        return new KafkaConsumer<>(props);
    }

}