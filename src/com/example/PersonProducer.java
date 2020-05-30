package com.example;

import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Sample kafka producer that produces Person objects on "PersonTopic2" Topic.
 * 
 * @author
 *
 */
public class PersonProducer {

	public static void main(String[] argv) throws Exception {

		String topicName = "PersonTopic2";

		// Configuration
		Properties configProperties = new Properties();
		configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.ByteArraySerializer");
		configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "com.example.PersonSerializer");

		org.apache.kafka.clients.producer.Producer producer = new KafkaProducer(configProperties);

		ObjectMapper objectMapper = new ObjectMapper();

		for (int i = 0; i < 4; i++) {
			Person p = new Person();
			p.setName("Ganesh Patil");
			if (i % 2 == 0)
				p.setCountry("India");
			else
				p.setCountry("USA");

			p.setPersonalID("" + i);
			p.setOccupation("Software Analyst [" + new Date() + "]");

			ProducerRecord<String, Person> rec = new ProducerRecord<String, Person>(topicName, p);
			producer.send(rec);
			System.out.println("message sent : " + rec);
			Thread.sleep(100);
		}
		producer.close();
	}
}