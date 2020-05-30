/******************************************************************************************/
Demonstrates different Kafka examples such as
i) Kafka Producer
ii) Kafka Consumer
iii) Kafka Streams
iv) Kafka Streams Filtering & Conditional Redirection
/******************************************************************************************/

Assumptions:

These examples have been tested using kafka_2.13-2.5.0 and Java 8.

To run these examples, 

i) zookeeper should be running:

C:\kafka\kafka_2.13-2.5.0\bin\windows>zookeeper-server-start.bat ..\..\config\zookeeper.properties
[2020-05-27 20:25:40,094] INFO Reading configuration from: ..\..\config\zookeeper.properties (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2020-05-27 20:25:40,098] WARN ..\..\config\zookeeper.properties is relative. Prepend .\ to indicate that you're sure! (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2020-05-27 20:25:40,111] WARN \tmp\zookeeper is relative. Prepend .\ to indicate that you're sure! (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2020-05-27 20:25:40,126] INFO clientPortAddress is 0.0.0.0:2181 (org.apache.zookeeper.server.quorum.QuorumPeerConfig)

ii) Kafka server should  be running:


c:\kafka\kafka_2.13-2.5.0\bin\windows>kafka-server-start.bat ..\..\config\server.properties
[2020-05-30 15:41:00,324] INFO Registered kafka:type=kafka.Log4jController MBean (kafka.utils.Log4jControllerRegistration$)
[2020-05-30 15:41:02,149] INFO Setting -D jdk.tls.rejectClientInitiatedRenegotiation=true to disable client-initiated TLS renegotiation (org.apache.zookeeper.common.X509Util)
[2020-05-30 15:41:02,534] INFO starting (kafka.server.KafkaServer)
[2020-05-30 15:41:02,536] INFO Connecting to zookeeper on localhost:2181 (kafka.server.KafkaServer)
[2020-05-30 15:41:02,630] INFO [ZooKeeperClient Kafka server] Initializing a new session to localhost:2181. (kafka.zookeeper.ZooKeeperClient)

iii) Kafka topics that the examples use should be created.
A sample topic can be created as follows:


c:\kafka\kafka_2.13-2.5.0\bin\windows>kafka-topics.bat  --create --zookeeper localhost:2181 --replication-factor 1   --partitions 1 --topic PersonOutputUSAUsersTopic
Created topic PersonOutputUSAUsersTopic.

c:\kafka\kafka_2.13-2.5.0\bin\windows>

iv) Kafka consumer can be run from command prompt like this:


c:\kafka\kafka_2.13-2.5.0\bin\windows>kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic PersonOutputIndiaUsersTopic
0,GEETA A. PATIL,INDIA,SOFTWARE ANALYST [SAT MAY 30 13:03:11 PDT 2020]
2,GEETA A. PATIL,INDIA,SOFTWARE ANALYST [SAT MAY 30 13:03:12 PDT 2020]
0,AMIT A. PATIL,INDIA,SOFTWARE ANALYST [SAT MAY 30 13:11:15 PDT 2020]
2,AMIT A. PATIL,INDIA,SOFTWARE ANALYST [SAT MAY 30 13:11:16 PDT 2020]


c:\kafka\kafka_2.13-2.5.0\bin\windows>kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic PersonOutputUSAUsersTopic

Topics "PersonOutputIndiaUsersTopic" and "PersonOutputUSAUsersTopic" have be used in the examples to demonstrate how 
we can do kafka streams condition based filtering and redirection to different topics.



