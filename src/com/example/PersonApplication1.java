package com.example;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import java.util.Properties;

/**
 * 
 * Demonstrates how to filter kafka stream. Records received from kafka topic
 * will be filtered based on user's country "INDIA".
 * 
 * @author
 *
 */
public class PersonApplication1 {

	public static void main(final String[] args) throws Exception {
		final Deserializer<PersonDeserializer> deserializer = new PersonDeserializer();

		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "person-application");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, PersonSerde.class.getName());

		StreamsBuilder builder = new StreamsBuilder();
		KStream<String, Person> textLines = builder.stream("PersonTopic2");

		textLines.filter((key, value) -> {
			System.out.println("key:" + key);
			System.out.println("value:" + value.getName());
			return value.getCountry().equalsIgnoreCase("INDIA");
		}

		).foreach((w, c) -> {

			System.out.println("Processing record:");
			System.out.println("record : " + w + " -> " + c.getName() + " " + c.getCountry() + " " + c.getOccupation());
		});

		KafkaStreams streams = new KafkaStreams(builder.build(), props);
		streams.start();
	}

}