# 此工程为kafka简单的应用和介绍

[kafka-example](https://github.com/tanjiquan/kafka-application/tree/master/kafka-example/readme.md)
主要是简单操作kafka，其中包含对生产、消费kafka数据demo，主要是对一些参数的介绍和认识。

1、序列化
<br>1.1 简单的 StringSerializer 序列化，StringDeserializer 反序列化
<br> &emsp;&emsp;  StringSerializer、StringDeserializer 类很简单，就是实现 Serializer 接口，但是注意这个接口是
org.apache.kafka.common.serialization包下面的，String序列化很简单，就是把字符串和byte[]数组进行
相互转换，编码默认为UTF8。其实到最后，kafka接收和发送的都是以字节数据从形式。

<br>1.2 自定义序列化类型
<br> &emsp;&emsp;  自定义序列化类其实比较简单，简单一句话就是实现org.apache.kafka.common.serialization中的Serializer和Deserializer接口。
     并且序列化的类（MessageData）也要实现Serializable 接口，最好重写 toString 方法，便于打印日志。
<br> 其中MessageDataSerializer 就是将传入的 MessageData 对象数据转换为byte[]，这里采用的是  JSON.toJSONBytes(data);
<br> MessageDataDeSerializer 就是将 byte[] 转化为MessageData对象，这里采用JSON.parseObject(data, MessageData.class);
<br> 上面的序列化和反序列化类和String 的序列换和反序列化及其相似。

<br>1.3 avro 序列化/反序列化 <br> 
&emsp;&emsp;  JSON是一种常用的数据交换格式，很多系统都会使用JSON作为数据接口返回的数据格式，然而，由于JSON数据中包含大量的字段名字，
导致空间的严重浪费，尤其是数据文件较大的时候，而AVRO是一种更加紧凑的数据序列化系统，占用空间相对较少，更利于数据在网络当中的传输.
首先我们要将嵌套的JSON数据与AVRO文件的相互转换，主要使用[avro-tools](http://mirrors.hust.edu.cn/apache/avro/avro-1.8.2/java/)
工具对这两种文件格式进行转换。在Hadoop的生态中，一般使用avro传输数据较为常见。

（1）定义json对应的avro数据结构，数据结构大致如下：
json数据:
```json
{
	"data": [{
		"dataCommit": "commit",
		"dataId": 1
	}],
	"recordID": "1059390768",
	"sendIp": "127.0.0.1",
	"sendTime": 1539877033494
}
```
对应avro数据格式:
```txt
{
  "namespace": "com.tt.kafka.example.avro",
  "type": "record",
  "name": "MessageData",
  "fields": [
    {"name": "sendTime", "type": "long"},
    {"name": "recordID",  "type": "string"},
    {"name": "sendIp",  "type": "string"},
    {"name": "datas",  "type": "array",
        "items":{
             "type":"record",
             "name" : "InnerData",
             "fields":[
                    {"name":"dataId","type":"int"},
                    {"name":"dataCommit","type":"string"}
              ]}
    }
  ]
}
```
对于以上 MessageData.avsc 文件需要注意
* 类型为array，因为从前面的JSON数据上可以看到，整个JSON数据是一个数组，因此，这里的类型为array，当类型为array时，必须指定items。
* 由于数组内部是记录形式，因此，在items里面的type是record，这里必须指定name和fields。
* fields里的name必须与JSON数据里的字段名保持一致。
* 值得注意的是，从前面的JSON数据可以看到，data信息为数组字段，因此，必须先指定name为Data，
而且type是一个对象，并不能单纯地指定为array，而是需要在对象里再用一个type来指定array，然后再加上items。
（2）根据 MessageData.avsc 文件生成 .java 文件，会生成 InnerData.java 和 MessageData.java
java -jar /path/to/avro-tools-1.8.0.jar compile schema <schema file> <destination>
java -jar /.../resources/avro/avro-tools-1.8.2.jar compile schema /../MessageData.avsc  ./




<br>&emsp;&emsp; 1.3 自定义序列化类型
