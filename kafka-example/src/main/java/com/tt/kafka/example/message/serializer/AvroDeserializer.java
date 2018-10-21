package com.tt.kafka.example.message.serializer;

import org.apache.avro.Schema;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

/**
 * description
 *
 * @author tanjiquan [tan_jiquan@163.com]
 * @date 2018/10/19 21:52
 * @since 1.0
 */
public class AvroDeserializer<T extends SpecificRecordBase> implements Deserializer<T> {
    private SpecificDatumReader<T> reader;

    public static String SCHEMA_CONFIG = "deserializer.avro.schema";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Schema schema = (Schema) configs.get(SCHEMA_CONFIG);
        reader = new SpecificDatumReader<T>(schema);
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        BinaryDecoder binaryDecoder = DecoderFactory.get().binaryDecoder(data, null);
        try {
            T obj = reader.read(null, binaryDecoder);
            return obj;
        } catch (IOException e) {
            throw new RuntimeException("error when deserialize avro binary", e);
        }
    }

    @Override
    public void close() {

    }
}
