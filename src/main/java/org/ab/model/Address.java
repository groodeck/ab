package org.ab.model;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Joiner;

public class Address {

	private String addressId;
	private String city;
	private String zipCode;
	private String street;
	private String houseNo;
	private String apartamentNo;

	public String getAddressId() {
		return addressId;
	}
	public String getApartamentNo() {
		return apartamentNo;
	}
	public String getCity() {
		return city;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public String getOneLineAddress() {
		return Joiner.on(" ").skipNulls().join(zipCode, city, getStreetDetails());
	}
	public String getStreet() {
		return street;
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
	public String getZipCode() {
		return zipCode;
	}
	public void setAddressId(final String addressId) {
		this.addressId = addressId;
	}
	public void setApartamentNo(final String apartamentNo) {
		this.apartamentNo = apartamentNo;
	}

	public void setCity(final String city) {
		this.city = city;
	}
	public void setHouseNo(final String houseNo) {
		this.houseNo = houseNo;
	}
	public void setStreet(final String street) {
		this.street = street;
	}
	public void setZipCode(final String zipCode) {
		this.zipCode = zipCode;
	}
}
