package com.example;

import java.util.Properties;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

/**
 * It demonstrates filtering of kafka stream based on the user's country. e.g.
 * User records from India will be directed to "PersonOutputStringTopic" Topic.
 * e.g. User records from USA will be directed to "PersonOutputUSAUsersTopic"
 * Topic.
 * 
 * @author
 *
 */
public class PersonApplication4 {

	public static void main(final String[] args) throws Exception {
		final Deserializer<PersonDeserializer> deserializer = new PersonDeserializer();

		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "person-application");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, PersonSerde.class.getName());

		StreamsBuilder builder = new StreamsBuilder();
		KStream<String, Person> textLines = builder.stream("PersonTopic2",
				Consumed.with(Serdes.String(), new PersonSerde()));

		KStream<String, Person> textLines1 = textLines;

		KStream<String, String> upperCasedStream = textLines.filter((key, value) -> {
			System.out.println("key:" + key);
			System.out.println("value:" + value.getName());
			return value.getCountry().equalsIgnoreCase("INDIA");
		}

		).mapValues(v -> v.getName().toUpperCase());

		KStream<String, Person> newtextLines = textLines.filter((key, value) -> {
			System.out.println("key:" + key);
			System.out.println("value:" + value.getName());
			return value.getCountry().equalsIgnoreCase("INDIA");
		}

		);

		newtextLines.to("PersonOutputTopic", Produced.with(Serdes.String(), new PersonSerde()));

		upperCasedStream.to("PersonOutputStringTopic", Produced.with(Serdes.String(), Serdes.String()));

		KStream<String, String> indiaUsersStream = textLines.filter((key, value) -> {
			System.out.println("key:" + key);
			System.out.println("value:" + value.getName());
			return value.getCountry().equalsIgnoreCase("INDIA");
		}

		).mapValues(v -> {

			return v.getPersonalID() + "," + v.getName().toUpperCase() + "," + v.getCountry().toUpperCase() + ","
					+ v.getOccupation().toUpperCase();

		});

		indiaUsersStream.to("PersonOutputIndiaUsersTopic", Produced.with(Serdes.String(), Serdes.String()));

		KStream<String, String> usaUsersStream = textLines1.filter((key, value) -> {
			System.out.println("key:" + key);
			System.out.println("value:" + value.getName());
			return value.getCountry().equalsIgnoreCase("USA");
		}

		).mapValues(v -> {

			return v.getPersonalID() + "," + v.getName().toUpperCase() + "," + v.getCountry().toUpperCase() + ","
					+ v.getOccupation().toUpperCase();

		});

		usaUsersStream.to("PersonOutputUSAUsersTopic", Produced.with(Serdes.String(), Serdes.String()));

		KafkaStreams streams = new KafkaStreams(builder.build(), props);
		streams.start();
	}

}