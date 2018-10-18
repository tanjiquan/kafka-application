package com.tt.kafka.example.message;

import java.io.Serializable;

/**
 * 具体数据
 *
 * @author tanjiquan [tan_jiquan@163.com]
 * @date 2018/10/18 22:06
 * @since 1.0
 */
public class InnerData implements Serializable {

    private static final long serialVersionUID = -655482737530685395L;

    private Integer dataId;

    private String dataCommit;

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public String getDataCommit() {
        return dataCommit;
    }

    public void setDataCommit(String dataCommit) {
        this.dataCommit = dataCommit;
    }

    @Override
    public String toString() {
        return "InnerData{" +
                "dataId=" + dataId +
                ", dataCommit='" + dataCommit + '\'' +
                '}';
    }
}
