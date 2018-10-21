package com.tt.kafka.example.producer;

import com.tt.kafka.example.avro.AvroMessageData;
import com.tt.kafka.example.constant.KafkaParam;
import com.tt.kafka.example.message.generator.MessageGenerator;
import com.tt.kafka.example.message.serializer.AvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * avro 序列化方式 生产端
 *
 * @author tanjiquan [tan_jiquan@163.com]
 * @date 2018/10/18 11:13
 * @since 1.0
 */
public class AvroSerializerProducer {

    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaParam.BROKER_LIST);
        props.put("acks", "all");
        props.put("retries", 3);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "com.tt.kafka.example.message.serializer.AvroSerializer");
        props.put(AvroSerializer.SCHEMA_CONFIG, AvroMessageData.SCHEMA$);

        int wait = 10000;
        Producer<String, AvroMessageData> producer = new KafkaProducer<>(props);
        while (true) {
            ProducerRecord<String, AvroMessageData> producerRecord = new ProducerRecord(KafkaParam.STRING_TOPIC, "", MessageGenerator.avroMessageNext());
            Future<RecordMetadata> future = producer.send(producerRecord);
            System.out.println("send avroSerializer messageData: " + producerRecord.value());
            try {
                System.out.println("get: " + future.get().toString());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            Thread.sleep(wait);
        }
    }

}