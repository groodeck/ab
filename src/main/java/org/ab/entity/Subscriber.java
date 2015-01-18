package org.ab.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.ab.model.dictionary.ClientType;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

@Entity
@Table(name="Subscriber")
public class Subscriber {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="subscriberId")
	private Integer subscriberId;

	@Column(name="subscriberIdn")
	private String subscriberIdn;

	@Enumerated(EnumType.STRING)
	@Column(name="clientType")
	private ClientType clientType;

	@Column(name="name")
	private String name;

	@Column(name="surname")
	private String surname;

	@Column(name="companyName")
	private String companyName;

	@Column(name="clientIdNumber")
	private String clientIdNumber;

	@Column(name="pesel")
	private String pesel;

	@Column(name="regon")
	private String regon;

	@Column(name="nip")
	private String nip;

	@Column(name="balance")
	private BigDecimal balance;

	@OneToMany(cascade={CascadeType.ALL}, orphanRemoval=true)
	@JoinColumn(name="subscriberId")
	private List<Contract> contracts;

	@OneToMany(cascade={CascadeType.ALL}, orphanRemoval=true)
	@JoinColumn(name="subscriberId")
	private List<Contact> contacts;

	@OneToMany(cascade={CascadeType.ALL}, orphanRemoval=true)
	@JoinColumn(name="subscriberId")
	private List<Address> addresses;

	@Column(name="comment")
	private String comment;

	@Column(name="additionalComment")
	private String additionalComment;

	public Subscriber(){
		contracts = Lists.newArrayList();
		contacts = Lists.newArrayList();
		addresses = Lists.newArrayList();
	}
	public String getAdditionalComment() {
		return additionalComment;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public String getClientIdNumber() {
		return clientIdNumber;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public String getComment() {
		return comment;
	}

	public String getCompanyName() {
		return companyName;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public String getEffectiveName() {
		if(clientType == ClientType.INDIVIDUAL){
			return Joiner.on(" ").skipNulls().join(name, surname);
		} else {
			return companyName;
		}
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

	public String getRegon() {
		return regon;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public String getSubscriberIdn() {
		return subscriberIdn;
	}

	public String getSurname() {
		return surname;
	}

	public void setAdditionalComment(final String additionalComment) {
		this.additionalComment = additionalComment;
	}

	public void setAddresses(final List<Address> addresses) {
		this.addresses = addresses;
	}

	public void setBalance(final BigDecimal balance) {
		this.balance = balance;
	}

	public void setClientIdNumber(final String clientIdNumber) {
		this.clientIdNumber = clientIdNumber;
	}

	public void setClientType(final ClientType clientType) {
		this.clientType = clientType;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}

	public void setCompanyName(final String companyName) {
		this.companyName = companyName;
	}

	public void setContacts(final List<Contact> contacts) {
		this.contacts = contacts;
	}

	public void setContracts(final List<Contract> contract) {
		contracts = contract;
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

	public void setRegon(final String regon) {
		this.regon = regon;
	}

	public void setSubscriberId(final Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	public void setSubscriberIdn(final String subscriberIdn) {
		this.subscriberIdn = subscriberIdn;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}
}
