# kafka-example kafka加单介绍

**kafka单机版安装**

以下安装过程比较简单，只是一个单机版的操作，集群安装检查请自行参照官网。

一、zookeeper安装
<br> 1.wget http://apache.fayea.com/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz
<br> 2.mkdir /home/tanjiquan/zookeeper/
<br> 3.tar -zxvf zookeeper-3.4.10.tar.gz -C /home/tanjiquan/zookeeper
<br> 4.cd zookeeper-3.4.10/
<br> 5.mkdir data
<br> 6.mkdir logs
<br> 7.cd conf
<br> 8.cp zoo_sample.cfg zoo.cfg 
<br> 9.vi zoo.cfg 配置
<br> dataDir=/home/tanjiquan/zookeeper/zookeeper-3.4.10/data
<br> dataLogDir=/home/tanjiquan/zookeeper/zookeeper-3.4.10/logs
<br> 10.vi /etc/profile
<br> ZOOKEEPER_HOME=/home/tanjiquan/zookeeper/zookeeper-3.4.10
<br> PATH=$ZOOKEEPER_HOME/bin:$PATH
<br> CLASSPATH=$ZOOKEEPER_HOME/lib:
<br> 11.source /etc/profile
<br> 12.sh zkServer.sh start

二、kafka安装
<br> 1.wget http://apache.fayea.com/kafka/0.11.0.3/kafka_2.12-0.11.0.3.tgz
<br> 2.mkdir kafka
<br> 3.tar -zxvf kafka_2.12-0.11.0.3.tgz -C /home/tanjiquan/kafka
<br> 4.cd kafka/kafka_2.12-0.11.0.3/
<br> 5.mkdir logs
<br> 6.cd config
<br> 7.vi server.properties
<br> listeners=PLAINTEXT://192.168.110.24:9092
<br> log.dirs=/home/tanjiquan/kafka/kafka_2.12-0.11.0.3/logs
<br> 13.vi /etc/profile
<br> KAFKA_HOME=/home/tanjiquan/kafka/kafka_2.12-0.11.0.3
<br> PATH=$KAFKA_HOME/bin:$PATH
<br> 14.source /etc/profile
<br> 15.nohup /home/tanjiquan/kafka/kafka_2.12-0.11.0.3/bin/kafka-server-start.sh /home/tanjiquan/kafka/kafka_2.12-0.11.0.3/config/server.properties >>/home/tanjiquan/kafka/kafka_2.12-0.11.0.3/kafka.log &

