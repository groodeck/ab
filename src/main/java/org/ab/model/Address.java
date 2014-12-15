package org.ab.model;

import org.apache.commons.lang.StringUtils;

public class Address {

	private String city;
	private String zipCode;
	private String street;
	private String houseNo;
	private String apartamentNo;
	
	public String getCity() {
		return city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public String getStreet() {
		return street;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public String getApartamentNo() {
		return apartamentNo;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public void setApartamentNo(String apartamentNo) {
		this.apartamentNo = apartamentNo;
	}
	
	public String getStreetDetails(){
		if(StringUtils.isNotBlank(street)){
			final StringBuilder sb = new StringBuilder(street);
			if(StringUtils.isNotBlank(houseNo)){
				sb.append(" " + houseNo);
			}
			if(StringUtils.isNotBlank(apartamentNo)){
				sb.append("/" + apartamentNo);
			}
			return sb.toString();
		} else {
			return "";
		}
	}
}
