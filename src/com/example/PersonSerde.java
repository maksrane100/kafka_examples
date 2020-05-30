package com.example;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

/**
 * PersonSerde demonstrates how custom serialization can be done in kafka.
 * 
 * @author
 *
 */
public class PersonSerde implements Serde<Person> {

	// These are both null unless you initialize them
	private PersonSerializer personSerializer = new PersonSerializer();
	private PersonDeserializer personDeserializer = new PersonDeserializer();

	@Override
	public Deserializer<Person> deserializer() {
		// TODO Auto-generated method stub
		return personDeserializer;
	}

	@Override
	public Serializer<Person> serializer() {
		// TODO Auto-generated method stub
		return personSerializer;
	}

}
