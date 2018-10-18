/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.tt.kafka.example.message.serializer;

import com.alibaba.fastjson.JSON;
import com.tt.kafka.example.message.MessageData;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * MessageData 自定义消息序列化类
 *
 * @author tanjiquan [tan_jiquan@163.com]
 * @date 2018/10/18 16:41
 * @since 1.0
 */
public class MessageDataSerializer implements Serializer<MessageData> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, MessageData data) {
        return JSON.toJSONBytes(data);
    }

    @Override
    public void close() {

    }
}