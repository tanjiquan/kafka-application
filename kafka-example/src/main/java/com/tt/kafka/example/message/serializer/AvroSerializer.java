package com.tt.kafka.example.message.serializer;

import org.apache.avro.Schema;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * AvroSerializer
 *
 * @author tanjiquan [tan_jiquan@163.com]
 * @date 2018/10/19 21:50
 * @since 1.0
 */
public class AvroSerializer <T extends SpecificRecordBase> implements Serializer<T> {

    public static String SCHEMA_CONFIG = "serializer.avro.schema";

    private SpecificDatumWriter<T> _writer;
    private BinaryEncoder _binaryEncoder = EncoderFactory.get().binaryEncoder(new ByteArrayOutputStream(), null);


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Schema schema = (Schema) configs.get(SCHEMA_CONFIG);
        _writer = new SpecificDatumWriter<>(schema);
    }


    @Override
    public byte[] serialize(String topic, T data) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        BinaryEncoder binaryEncoder = EncoderFactory.get().binaryEncoder(stream, _binaryEncoder);
        try {
            _writer.write(data, binaryEncoder);
            binaryEncoder.flush();
            return stream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("error when enocde avro object to binary", e);
        }
    }

    @Override
    public void close() {
    }
}
