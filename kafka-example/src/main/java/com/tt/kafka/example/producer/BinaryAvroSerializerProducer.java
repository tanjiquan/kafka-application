package com.tt.kafka.example.producer;

import com.tt.kafka.example.avro.AvroMessageData;
import com.tt.kafka.example.constant.KafkaParam;
import com.tt.kafka.example.message.generator.MessageGenerator;
import com.tt.kafka.example.message.serializer.AvroSerializer;
import com.twitter.bijection.Injection;
import com.twitter.bijection.avro.SpecificAvroCodecs;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * avro 序列化方式 生产端, 不使用Schema Registry。
 *
 * 把Avro转成byte[]发送。依赖com.twitter:bijection-avro_${sacla_version
 *
 * @author tanjiquan [tan_jiquan@163.com]
 * @date 2018/10/18 11:13
 * @since 1.0
 */
public class BinaryAvroSerializerProducer {

    public static void main(String[] args) throws InterruptedException {


        Properties props = new Properties();
        // hardcoding the Kafka server URfor this example
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        props.put(ProducerConfig.METADATA_MAX_AGE_CONFIG, "10000");//改成10秒，以演示增加partition的情况

        int wait = 10000;

        Producer<String, byte[]> producer = new KafkaProducer<>(props);

        Injection<AvroMessageData, byte[]> specificBinaryInjection = SpecificAvroCodecs.toBinary(AvroMessageData.SCHEMA$);

        while (true) {
            AvroMessageData messageData = MessageGenerator.avroMessageNext();
            byte[] eventBytes = specificBinaryInjection.apply(messageData);
            System.out.println("Generated event " + messageData.toString());
            // Using IP as key, so events from same IP will go to same partition
            ProducerRecord<String, byte[]> record = new ProducerRecord<>(KafkaParam.STRING_TOPIC, messageData.getSendIp().toString(), eventBytes);
            Future<RecordMetadata> future = producer.send(record);
            try {
                RecordMetadata metadata = future.get();
                System.out.println("sent BinaryAvroSerializerProducer: " + metadata);
                Thread.sleep(wait);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}