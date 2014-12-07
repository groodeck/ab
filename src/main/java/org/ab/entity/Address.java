package org.ab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.ab.model.dictionary.AddressType;

@Entity
@Table(name="Address")
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="addressId")
	private Integer addressId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="addressType")
	private AddressType addressType;
	
	@Column(name="city")
	private String city;
	
	@Column(name="zipCode")
	private String zipCode;
	
	@Column(name="street")
	private String street;
	
	@Column(name="houseNo")
	private String houseNo;
	
	@Column(name="apartmentNo")
	private String apartmentNo;
	
	@ManyToOne
    @JoinColumn(name="subscriberId", insertable=false, updatable=false, nullable=false)
	private Subscriber subscriber;

	public Address(){
	}
	
	public Address(final AddressType addressType, final String city, final String zipCode,
			final String street, final String houseNo, final String apartmentNo) {
		this.addressType = addressType;
		this.city = city;
		this.zipCode = zipCode;
		this.street = street;
		this.houseNo = houseNo;
		this.apartmentNo = apartmentNo;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public AddressType getAddressType() {
		return addressType;
	}

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

	public String getApartmentNo() {
		return apartmentNo;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
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

	public void setApartmentNo(String apartmentNo) {
		this.apartmentNo = apartmentNo;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

}
