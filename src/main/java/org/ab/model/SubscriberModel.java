package org.ab.model;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class SubscriberModel {

	private String subscriberId;
	private String subscriberIdn;
	private String subscriberStatusIdn;
	private String subscriberStatusDesc;
	private String clientType;
	private String clientTypeDesc;
	private String name;
	private String surname;
	private String companyName;
	private String clientIdNumber;
	private String pesel;
	private String regon;
	private String nip;

	private Contract currentContract;
	private String balance;
	private String comment;
	private String additionalComment;

	private Address mainAddress;
	private Address serviceAddress;
	private boolean serviceAddressSet;
	private Address correspondenceAddress;
	private boolean correspondenceAddressSet;

	private List<String> phoneNumbers;
	private List<String> emails;

	public SubscriberModel(){
		balance = BigDecimal.ZERO.setScale(2).toPlainString();
		phoneNumbers = Lists.newArrayList("");
		emails = Lists.newArrayList("");
		currentContract = new Contract();
	}

	public String getAdditionalComment() {
		return additionalComment;
	}
	public String getBalance() {
		return balance;
	}
	public String getClientIdNumber() {
		return clientIdNumber;
	}
	public String getClientType() {
		return clientType;
	}
	public String getClientTypeDesc() {
		return clientTypeDesc;
	}
	public String getComment() {
		return comment;
	}
	public String getCompanyName() {
		return companyName;
	}
	public Address getCorrespondenceAddress() {
		return correspondenceAddress;
	}
	public Contract getCurrentContract() {
		return currentContract;
	}
	public String getEffectiveName(){
		if(StringUtils.isNotBlank(companyName)){
			return companyName;
		} else {
			return Joiner.on(" ").join(name, surname);
		}
	}
	public List<String> getEmails() {
		return emails;
	}
	public Address getMainAddress() {
		return mainAddress;
	}
	public String getName() {
		return name;
	}
	public String getNip() {
		return nip;
	}
	public String getPesel() {
		return pesel;
	}
	public String getPhoneList(){
		return Joiner.on("<br/>").join(phoneNumbers);
	}
	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	public String getRegon() {
		return regon;
	}
	public Address getServiceAddress() {
		return serviceAddress;
	}
	public String getSubscriberId() {
		return subscriberId;
	}
	public String getSubscriberIdn() {
		return subscriberIdn;
	}
	public String getSubscriberStatusDesc() {
		return subscriberStatusDesc;
	}
	public String getSubscriberStatusIdn() {
		return subscriberStatusIdn;
	}
	public String getSurname() {
		return surname;
	}
	public boolean isCorrespondenceAddressSet() {
		return correspondenceAddressSet;
	}
	public boolean isServiceAddressSet() {
		return serviceAddressSet;
	}
	public void setAdditionalComment(final String additionalComment) {
		this.additionalComment = additionalComment;
	}
	public void setBalance(final String balance) {
		this.balance = balance;
	}
	public void setClientIdNumber(final String clientIdNumber) {
		this.clientIdNumber = clientIdNumber;
	}
	public void setClientType(final String clientType) {
		this.clientType = clientType;
	}
	public void setClientTypeDesc(final String clientTypeDesc) {
		this.clientTypeDesc = clientTypeDesc;
	}
	public void setComment(final String comment) {
		this.comment = comment;
	}

	public void setCompanyName(final String companyName) {
		this.companyName = companyName;
	}

	public void setCorrespondenceAddress(final Address correspondenceAddress) {
		this.correspondenceAddress = correspondenceAddress;
	}

	public void setCorrespondenceAddressSet(final boolean correspondenceAddressSet) {
		this.correspondenceAddressSet = correspondenceAddressSet;
	}

	public void setCurrentContract(final Contract currentContract) {
		this.currentContract = currentContract;
	}

	public void setEmails(final List<String> emails) {
		this.emails = emails;
	}

	public void setMainAddress(final Address mainAddress) {
		this.mainAddress = mainAddress;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setNip(final String nip) {
		this.nip = nip;
	}

	public void setPesel(final String pesel) {
		this.pesel = pesel;
	}

	public void setPhoneNumbers(final List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public void setRegon(final String regon) {
		this.regon = regon;
	}

	public void setServiceAddress(final Address serviceAddress) {
		this.serviceAddress = serviceAddress;
	}

	public void setServiceAddressSet(final boolean serviceAddressSet) {
		this.serviceAddressSet = serviceAddressSet;
	}

	public void setSubscriberId(final String subscriberId) {
		this.subscriberId = subscriberId;
	}

	public void setSubscriberIdn(final String subscriberIdn) {
		this.subscriberIdn = subscriberIdn;
	}

	public void setSubscriberStatusDesc(final String subscriberStatusDesc) {
		this.subscriberStatusDesc = subscriberStatusDesc;
	}

	public void setSubscriberStatusIdn(final String subscriberStatusIdn) {
		this.subscriberStatusIdn = subscriberStatusIdn;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}
}
