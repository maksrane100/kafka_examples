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
 * Orders from India will be directed to "IndiaOrdersOutputTopic"
 * Topic. e.g. Orders from USA will be directed to
 * "USAOrdersOutputTopic" Topic.
 * 
 * @author
 *
 */
public class OrderDetailsApplication {

	public static void main(final String[] args) throws Exception {
		final Deserializer<OrderDetailsDeserializer> deserializer = new OrderDetailsDeserializer();

		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "order-application");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, OrderDetailsSerde.class.getName());

		
		StreamsBuilder builder = new StreamsBuilder();
		KStream<String, OrderDetails> textLines = builder.stream("OrderDetailsTopic1",
				Consumed.with(Serdes.String(), new OrderDetailsSerde()));

		KStream<String, OrderDetails> textLines1 = textLines;

		KStream<String, String> indiaUsersStream = textLines.filter((key, value) -> {
			System.out.println("key:" + key);
			System.out.println("value:" + value.getAddress1() + " " + value.getAddress2() + " " + value.getCity() + " "
					+ value.getState() + " " + value.getZip() + " " + value.getCountry());
			return value.getCountry().equalsIgnoreCase("INDIA");
		}

		).mapValues(value -> {

			return "RECORD :" + value.getAddress1() + " " + value.getAddress2() + " " + value.getCity() + " "
					+ value.getState() + " " + value.getZip() + " " + value.getCountry();

		});

		indiaUsersStream.to("IndiaOrdersOutputTopic", Produced.with(Serdes.String(), Serdes.String()));

		KStream<String, String> usaUsersStream = textLines1.filter((key, value) -> {
			System.out.println("key:" + key);
			System.out.println("value:" + value.getAddress1() + " " + value.getAddress2() + " " + value.getCity() + " "
					+ value.getState() + " " + value.getZip() + " " + value.getCountry());
			return value.getCountry().equalsIgnoreCase("USA");
		}

		).mapValues(value -> {

			return "RECORD :" + value.getAddress1() + " " + value.getAddress2() + " " + value.getCity() + " "
					+ value.getState() + " " + value.getZip() + " " + value.getCountry();

		});

		usaUsersStream.to("USAOrdersOutputTopic", Produced.with(Serdes.String(), Serdes.String()));

		KafkaStreams streams = new KafkaStreams(builder.build(), props);
		streams.start();
	}

}