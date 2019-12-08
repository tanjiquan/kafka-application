package com.kafka.example.producer;

import com.alibaba.fastjson.JSON;
import com.kafka.example.constant.KafkaParam;
import com.kafka.example.message.generator.MessageGenerator;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * StringSerializer 类型生产端
 *
 * @author tanjiquan [tan_jiquan@163.com]
 * @date 2018/10/18 11:13
 * @since 1.0
 */
public class StringSerializerProducer {

    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaParam.BROKER_LIST);
        props.put("acks", "all");
        props.put("retries", 3);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        int wait = 1000;
        Producer<String, String> producer = new KafkaProducer<>(props);
        while (true) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord(KafkaParam.STRING_TOPIC, "", JSON.toJSONString(MessageGenerator.messageNext()));
            Future<RecordMetadata> future = producer.send(producerRecord);
            System.out.println("send messageData: " + producerRecord.value());
            try {
                System.out.println("get: " + future.get().toString());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            Thread.sleep(wait);
        }
    }

}