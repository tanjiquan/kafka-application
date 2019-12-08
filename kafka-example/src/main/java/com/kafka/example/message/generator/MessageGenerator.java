package com.kafka.example.message.generator;

import com.alibaba.fastjson.JSON;
import com.kafka.example.avro.AvroInnerData;
import com.kafka.example.avro.AvroMessageData;
import com.kafka.example.message.InnerData;
import com.kafka.example.message.MessageData;

import java.util.ArrayList;
import java.util.List;
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

    public static MessageData messageNext() {
        Random r = new Random();

        Integer recordID = r.nextInt();

        MessageData message = new MessageData();
        message.setSendTime(System.currentTimeMillis());
        message.setRecordID(recordID.toString());
        message.setSendIp(IP);

        List dataList = new ArrayList();
        InnerData data1 = new InnerData();
        data1.setDataId(1);
        data1.setDataCommit("commit");
        dataList.add(data1);

        message.setDatas(dataList);

        return message;
    }

    public static AvroMessageData avroMessageNext() {
        Random r = new Random();

        Integer recordID = r.nextInt();

        AvroMessageData message = new AvroMessageData();
        message.setSendTime(System.currentTimeMillis());
        message.setRecordID(recordID.toString());
        message.setSendIp(IP);

        List dataList = new ArrayList();
        AvroInnerData data1 = new AvroInnerData();
        data1.setDataId(1);
        data1.setDataCommit("commit");
        dataList.add(data1);

        message.setDatas(dataList);

        return message;
    }

    public static void main(String[] args) {
        MessageData data = MessageGenerator.messageNext();
        System.out.print(JSON.toJSONString(data));
    }

}