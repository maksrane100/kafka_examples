package com.example;


import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderDetailsSerializer implements Serializer {

	@Override
	public byte[] serialize(String arg0, Object arg1) {
		System.out.println("OrderDetailsSerializer : in serialize");
		byte[] retVal = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			retVal = objectMapper.writeValueAsString((OrderDetails)arg1).getBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

	@Override
	public void close() {
	}
}