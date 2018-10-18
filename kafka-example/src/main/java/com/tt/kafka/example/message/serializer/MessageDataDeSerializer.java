/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.tt.kafka.example.message.serializer;

import com.alibaba.fastjson.JSON;
import com.tt.kafka.example.message.MessageData;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * MessageData.avsc 自定义消息反序列化类
 *
 * @author tanjiquan [tan_jiquan@163.com]
 * @date 2018/10/18 16:44
 * @since 1.0
 */
public class MessageDataDeSerializer implements Deserializer<MessageData> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public MessageData deserialize(String topic, byte[] data) {
        return JSON.parseObject(data, MessageData.class);
    }

    @Override
    public void close() {

    }
}