package com.example;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderDetailsDeserializer implements Deserializer {
	@Override
	public void close() {
	}

//	@Override
//	public void configure(Map<String, ?> arg0, boolean arg1) {
//	}

	@Override
	public OrderDetails deserialize(String arg0, byte[] arg1) {
		System.out.println("OrderDetailsDeserializer: in deserialize");
		ObjectMapper mapper = new ObjectMapper();
		OrderDetails orderDetails = null;
		try {
			orderDetails = mapper.readValue(arg1, OrderDetails.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderDetails;
	}
}