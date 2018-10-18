/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.tt.kafka.example.message.generator;

import com.tt.kafka.example.message.MessageData;

import java.util.Random;

/**
 * description
 *
 * @author tanjiquan [tan_jiquan@163.com]
 * @date 2018/10/18 11:36
 * @since 1.0
 */
public class MessageGenerator {

    public static final String IP = "127.0.0.1";

    public static MessageData stringMessageNext() {
        Random r = new Random();

        Integer recordID = r.nextInt();

        MessageData message = new MessageData();
        message.setSendTime(System.currentTimeMillis());
        message.setRecordID(recordID.toString());
        message.setSendIp(IP);
        message.setData("data");

        return message;
    }

}