/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.tt.kafka.example.message;

import java.io.Serializable;

/**
 * string 类型的消息数据
 *
 * @author tanjiquan [tan_jiquan@163.com]
 * @date 2018/10/18 11:23
 * @since 1.0
 */
public class MessageData implements Serializable {
    private static final long serialVersionUID = -6720863754163588518L;
    /**
     * 消息发送时间
     */
    private Long sendTime;

    /**
     * 消息唯一记录
     */
    private String recordID;

    /**
     * 消息发送端IP
     */
    private String sendIp;

    /**
     * 消息数据
     */
    private Object data;

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public String getRecordID() {
        return recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    public String getSendIp() {
        return sendIp;
    }

    public void setSendIp(String sendIp) {
        this.sendIp = sendIp;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MessageData{" +
                "sendTime=" + sendTime +
                ", recordID='" + recordID + '\'' +
                ", sendIp='" + sendIp + '\'' +
                ", data=" + data +
                '}';
    }
}