package com.example;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PersonDeserializer implements Deserializer {
	@Override
	public void close() {
	}

//	@Override
//	public void configure(Map<String, ?> arg0, boolean arg1) {
//	}

	@Override
	public Person deserialize(String arg0, byte[] arg1) {
		System.out.println("PersonDeserializer: in deserialize");
		ObjectMapper mapper = new ObjectMapper();
		Person person = null;
		try {
			person = mapper.readValue(arg1, Person.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;
	}
}