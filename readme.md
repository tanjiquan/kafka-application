# 此工程为kafka简单的应用和介绍

[kafka-example](https://github.com/tanjiquan/kafka-application/tree/master/kafka-example/readme.md)主要是简单操作kafka，其中包含对生产、消费kafka数据demo，主要是对一些参数的介绍和认识。

1、序列化
<br>1.1 简单的 StringSerializer 序列化，StringDeserializer 反序列化
<br> &emsp;&emsp;  StringSerializer、StringDeserializer 类很简单，就是实现 Serializer 接口，但是注意这个接口是
org.apache.kafka.common.serialization包下面的，String序列化很简单，就是把字符串和byte[]数组进行
相互转换，编码默认为UTF8。其实到最后，kafka接收和发送的都是以字节数据从形式。

<br> 1.2 自定义序列化类型
<br> &emsp;&emsp;  自定义序列化类其实比较简单，简单一句话就是实现org.apache.kafka.common.serialization中的Serializer和Deserializer接口。
     并且序列化的类（MessageData）也要实现Serializable 接口，最好重写 toString 方法，便于打印日志。
<br> 其中MessageDataSerializer 就是将传入的 MessageData 对象数据转换为byte[]，这里采用的是  JSON.toJSONBytes(data);
<br> MessageDataDeSerializer 就是将 byte[] 转化为MessageData对象，这里采用JSON.parseObject(data, MessageData.class);
<br> 上面的序列化和反序列化类和String 的序列换和反序列化及其相似。

<br> 1.3 avro序列化/反序列化
<br> &emsp;&emsp;  

<br>&emsp;&emsp; 1.3 自定义序列化类型
