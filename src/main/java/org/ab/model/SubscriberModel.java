package org.ab.model;

import java.util.List;

import com.google.common.collect.Lists;

public class SubscriberModel {
	
	private String subscriberId;
	private String clientType;
	private String name;
	private String surname;
	private String companyName;
	private String clientIdNumber;
	private String pesel;
	private String regon;
	private String nip;

	private Contract currentContract;
	private String balance;
	
	private Address mainAddress;
	private Address serviceAddress;
	private Address correspondenceAddress;
	
	private List<String> phoneNumbers;
	private List<String> emails;
	
	public SubscriberModel(){
		phoneNumbers = Lists.newArrayList("123", "234", "456");
		emails = Lists.newArrayList("34535");
	}
	
	public String getSubscriberId() {
		return subscriberId;
	}
	public String getClientType() {
		return clientType;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String getClientIdNumber() {
		return clientIdNumber;
	}
	public String getPesel() {
		return pesel;
	}
	public String getRegon() {
		return regon;
	}
	public String getNip() {
		return nip;
	}
	public Contract getCurrentContract() {
		return currentContract;
	}
	public Address getMainAddress() {
		return mainAddress;
	}
	public Address getServiceAddress() {
		return serviceAddress;
	}
	public Address getCorrespondenceAddress() {
		return correspondenceAddress;
	}
	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	public List<String> getEmails() {
		return emails;
	}
	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setClientIdNumber(String clientIdNumber) {
		this.clientIdNumber = clientIdNumber;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	public void setRegon(String regon) {
		this.regon = regon;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	public void setCurrentContract(Contract currentContract) {
		this.currentContract = currentContract;
	}
	public void setMainAddress(Address mainAddress) {
		this.mainAddress = mainAddress;
	}
	public void setServiceAddress(Address serviceAddress) {
		this.serviceAddress = serviceAddress;
	}
	public void setCorrespondenceAddress(Address correspondenceAddress) {
		this.correspondenceAddress = correspondenceAddress;
	}
	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public void setEmails(List<String> emails) {
		this.emails = emails;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}

}
