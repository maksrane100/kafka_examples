package com.example;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * OrderDetails POJO.
 * 
 * @author
 *
 */
@JsonRootName("orderdetails")
public class OrderDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String description;
	private String category;
	private Double latitude;
	private Double longitude;
	private Double price;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String country;

	public OrderDetails() {

	}

	@JsonCreator
	public OrderDetails(@JsonProperty("title") String title, @JsonProperty("description") String description,
			@JsonProperty("category") String category, @JsonProperty("latitude") Double latitude,
			@JsonProperty("longitude") Double longitude,

			@JsonProperty("address1") String address1, @JsonProperty("address2") String address2,
			@JsonProperty("city") String city, @JsonProperty("state") String state, @JsonProperty("zip") String zip,
			@JsonProperty("country") String country) {
		this.title = title;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;

		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String toString() {
		return this.title + " " + this.description + " " + this.latitude + " " + this.longitude;
	}

}