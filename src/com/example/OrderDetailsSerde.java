package com.example;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

/**
 * OrderDetailsSerde demonstrates how custom serialization can be done in kafka.
 * 
 * @author
 *
 */
public class OrderDetailsSerde implements Serde<OrderDetails> {

	// These are both null unless you initialize them
	private OrderDetailsSerializer orderDetailsSerializer = new OrderDetailsSerializer();
	private OrderDetailsDeserializer orderDetailsDeserializer = new OrderDetailsDeserializer();

	@Override
	public Deserializer<OrderDetails> deserializer() {
		// TODO Auto-generated method stub
		return orderDetailsDeserializer;
	}

	@Override
	public Serializer<OrderDetails> serializer() {
		// TODO Auto-generated method stub
		return orderDetailsSerializer;
	}

}
