package com.example;

import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Sample kafka producer that produces OrderDetails objects on "OrderDetailsTopic" Topic.
 * 
 * @author
 *
 */
public class OrderDetailsProducer {

	public static void main(String[] argv) throws Exception {

		String topicName = "OrderDetailsTopic1";

		// Configuration
		Properties configProperties = new Properties();
		configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.ByteArraySerializer");
		configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "com.example.OrderDetailsSerializer");

		org.apache.kafka.clients.producer.Producer producer = new KafkaProducer(configProperties);

		ObjectMapper objectMapper = new ObjectMapper();

		for (int i = 0; i < 4; i++) {
			OrderDetails o = new OrderDetails();
			o.setTitle("Latitude 3500 Laptop");
			o.setDescription("8th Generation Intel 3.9Ghz)");
			o.setCategory("Electronics");
			o.setPrice(569.00);
			o.setLatitude(37.66);
			o.setLongitude(121.87);
			
			if (i % 2 == 0) {
				o.setAddress1("A Wing, OM Villa Symphony, Great Road");
				o.setAddress2("Near Andheri Ground");
				o.setCity("Andheri");
				o.setState("Maharashtra");
				o.setZip("400069");
				o.setCountry("India");
			}
			else {
				o.setAddress1("1000 Foster City Blvd");
				o.setAddress2("Suite # 1200");
				o.setCity("Foster City");
				o.setState("CA");
				o.setZip("94404");
				o.setCountry("USA");
			}

			try {
			ProducerRecord<String, OrderDetails> rec = new ProducerRecord<String, OrderDetails>(topicName, o);
			producer.send(rec);
			System.out.println("message sent : " + rec);
			} catch(Exception ex) {
				System.out.println("Error occurred.");
			}
			Thread.sleep(1000);
		}
		producer.close();
	}
}
