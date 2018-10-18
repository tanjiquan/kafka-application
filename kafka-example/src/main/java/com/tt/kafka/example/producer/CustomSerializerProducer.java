package com.tt.kafka.example.producer;

import com.tt.kafka.example.constant.KafkaParam;
import com.tt.kafka.example.message.MessageData;
import com.tt.kafka.example.message.generator.MessageGenerator;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 自定义序列化类型 生产端
 *
 * @author tanjiquan [KF.tanjiquan@h3c.com]
 * @date 2018/10/18 16:26
 * @since 1.0
 */
public class CustomSerializerProducer {

    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaParam.BROKER_LIST);
        props.put("acks", "all");
        props.put("retries", 3);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "com.tt.kafka.example.message.serializer.MessageDataSerializer");

        int wait = 1000;
        Producer<String, MessageData> producer = new KafkaProducer<>(props);
        while (true) {
            ProducerRecord<String, MessageData> producerRecord = new ProducerRecord(KafkaParam.STRING_TOPIC, "", MessageGenerator.stringMessageNext());
            Future<RecordMetadata> future = producer.send(producerRecord);
            System.out.println("send messageDataSerializer messageData: " + producerRecord.value());
            try {
                System.out.println("get: " + future.get().toString());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            Thread.sleep(wait);
        }
    }

}