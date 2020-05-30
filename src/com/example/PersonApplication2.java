
package com.example;

import java.util.Properties;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

/**
 * 
 * Demonstrates how to filter kafka stream. Records received from kafka topic
 * will be filtered based on user's country "INDIA". It directs the filtered
 * kafka stream to Topic "PersonOutputTopic".
 * 
 * @author
 *
 */
public class PersonApplication2 {

	public static void main(final String[] args) throws Exception {
		final Deserializer<PersonDeserializer> deserializer = new PersonDeserializer();

		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "person-application");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, PersonSerde.class.getName());

		StreamsBuilder builder = new StreamsBuilder();
		KStream<String, Person> textLines = builder.stream("PersonTopic2");

		KStream<String, Person> newtextLines = textLines.filter((key, value) -> {
			System.out.println("key:" + key);
			System.out.println("value:" + value.getName());
			return value.getCountry().equalsIgnoreCase("INDIA");
		}

		);

		newtextLines.to("PersonOutputTopic", Produced.with(Serdes.String(), new PersonSerde()));

		KafkaStreams streams = new KafkaStreams(builder.build(), props);
		streams.start();
	}

}