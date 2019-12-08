# kafka-example kafka简单介绍

**kafka单机版安装**

以下安装过程比较简单，只是一个单机版的操作，集群安装检查请自行参照官网。

一、zookeeper安装
<br> 1.wget http://apache.fayea.com/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz
<br> 2.mkdir /Users/tanjiquan/software/hadoop/zookeeper
<br> 3.tar -zxvf zookeeper-3.4.10.tar.gz -C /Users/tanjiquan/software/hadoop/zookeeper
<br> 4.cd zookeeper-3.4.10/
<br> 5.mkdir data
<br> 6.mkdir logs
<br> 7.cd conf
<br> 8.cp zoo_sample.cfg zoo.cfg 
<br> 9.vi zoo.cfg 配置
<br> dataDir=/Users/tanjiquan/software/hadoop/zookeeper/zookeeper-3.4.10/data
<br> dataLogDir=/home/tanjiquan/zookeeper/zookeeper-3.4.10/logs
<br> 10.vi ~/.bash_profile 或者 vi /etc/profile
<br> ZOOKEEPER_HOME=/Users/tanjiquan/software/hadoop/zookeeper/zookeeper-3.4.10
<br> PATH=$ZOOKEEPER_HOME/bin:$PATH
<br> CLASSPATH=$ZOOKEEPER_HOME/lib:
<br> 11.source ~/.bash_profile (/etc/profile)
<br> 12.sh zkServer.sh start

二、kafka安装
<br> 1.wget http://apache.fayea.com/kafka/0.11.0.3/kafka_2.12-0.11.0.3.tgz
<br> 2.mkdir kafka
<br> 3.tar -zxvf kafka_2.12-0.11.0.3.tgz -C /Users/tanjiquan/software/hadoop/kafka
<br> 4.cd kafka/kafka_2.12-0.11.0.3/
<br> 5.mkdir logs
<br> 6.cd config
<br> 7.vi server.properties
<br> listeners=PLAINTEXT://127.0.0.1:9092
<br> log.dirs=/Users/tanjiquan/software/hadoop/kafka/kafka_2.12-0.11.0.3/kafka-logs
<br> 13.vi ~/.bash_profile 或者 vi /etc/profile
<br> KAFKA_HOME=/Users/tanjiquan/software/hadoop/kafka/kafka_2.12-0.11.0.3
<br> PATH=$KAFKA_HOME/bin:$PATH
<br> 14.source ~/.bash_profile (/etc/profile)
<br> 15.nohup /Users/tanjiquan/software/hadoop/kafka/kafka_2.12-0.11.0.3/bin/kafka-server-start.sh //Users/tanjiquan/software/hadoop/kafka/kafka_2.12-0.11.0.3/config/server.properties >>/Users/tanjiquan/software/hadoop/kafka/kafka_2.12-0.11.0.3/kafka.log &

三、kafka常用命令

<br> 1.启动kafka
/Users/tanjiquan/software/hadoop/kafka/kafka_2.12-0.11.0.3/bin/kafka-server-start.sh //Users/tanjiquan/software/hadoop/kafka/kafka_2.12-0.11.0.3/config/server.properties >>/Users/tanjiquan/software/hadoop/kafka/kafka_2.12-0.11.0.3/kafka.log &

<br> 2.创建topic
/Users/tanjiquan/software/hadoop/kafka/kafka_2.12-0.11.0.3/bin/kafka-topics.sh --create --zookeeper 127.0.0.1:2181 --replication-factor 1 --partitions 1 --topic test_topic

<br> 3.删除topic
/Users/tanjiquan/software/hadoop/kafka/kafka_2.12-0.11.0.3/bin/kafka-topics.sh --delete --zookeeper 127.0.0.1:2181 --topic test_topic 

<br> 4.查看topic列表
/Users/tanjiquan/software/hadoop/kafka/kafka_2.12-0.11.0.3/bin/kafka-topics.sh --list --zookeeper 127.0.0.1:2181

<br> 5.查看某个topic信息
/Users/tanjiquan/software/hadoop/kafka/kafka_2.12-0.11.0.3/bin/kafka-topics.sh --describe --zookeeper 127.0.0.1:2181  --topic test_topic

<br> 6.生产端cli
/Users/tanjiquan/software/hadoop/kafka/kafka_2.12-0.11.0.3/bin/kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic test_topic 

<br> 7.消费端cli
/Users/tanjiquan/software/hadoop/kafka/kafka_2.12-0.11.0.3/bin/kafka-console-consumer.sh --topic test_topic --bootstrap-server 127.0.0.1:9092 --from-beginning 

<br> 8.重新分配分区
