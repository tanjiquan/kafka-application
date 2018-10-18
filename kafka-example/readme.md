# kafka-example kafka加单介绍

**kafka单机版安装**

以下安装过程比较简单，只是一个单机版的操作，集群安装检查请自行参照官网。

一、zookeeper安装
1.wget http://apache.fayea.com/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz
2.mkdir /home/tanjiquan/zookeeper/
3.tar -zxvf zookeeper-3.4.10.tar.gz -C /home/tanjiquan/zookeeper
4.cd zookeeper-3.4.10/
5.mkdir data
6.mkdir logs
7.cd conf
8.cp zoo_sample.cfg zoo.cfg 
9.vi zoo.cfg 配置
dataDir=/home/tanjiquan/zookeeper/zookeeper-3.4.10/data
dataLogDir=/home/tanjiquan/zookeeper/zookeeper-3.4.10/logs
10.vi /etc/profile
ZOOKEEPER_HOME=/home/tanjiquan/zookeeper/zookeeper-3.4.10
PATH=$ZOOKEEPER_HOME/bin:$PATH
CLASSPATH=$ZOOKEEPER_HOME/lib:
11.source /etc/profile
12.sh zkServer.sh start

二、kafka安装
1.wget http://apache.fayea.com/kafka/0.11.0.3/kafka_2.12-0.11.0.3.tgz
2.mkdir kafka
3.tar -zxvf kafka_2.12-0.11.0.3.tgz -C /home/tanjiquan/kafka
4.cd kafka/kafka_2.12-0.11.0.3/
5.mkdir logs
6.cd config
7.vi server.properties
listeners=PLAINTEXT://192.168.110.24:9092
log.dirs=/home/tanjiquan/kafka/kafka_2.12-0.11.0.3/logs
13.vi /etc/profile
KAFKA_HOME=/home/tanjiquan/kafka/kafka_2.12-0.11.0.3
PATH=$KAFKA_HOME/bin:$PATH
14.source /etc/profile
nohup /home/tanjiquan/kafka/kafka_2.12-0.11.0.3/bin/kafka-server-start.sh /home/tanjiquan/kafka/kafka_2.12-0.11.0.3/config/server.properties >>/home/tanjiquan/kafka/kafka_2.12-0.11.0.3/kafka.log &

