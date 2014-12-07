package org.ab.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.ab.model.dictionary.ClientType;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

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
	public Integer getSubscriberId() {
		return subscriberId;
	}

	public String getSubscriberIdn() {
		return subscriberIdn;
	}

	public ClientType getClientType() {
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

	public BigDecimal getBalance() {
		return balance;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	public void setSubscriberIdn(String subscriberIdn) {
		this.subscriberIdn = subscriberIdn;
	}

	public void setClientType(ClientType clientType) {
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

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public void setContracts(List<Contract> contract) {
		this.contracts = contract;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public String getComment() {
		return comment;
	}

	public String getAdditionalComment() {
		return additionalComment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setAdditionalComment(String additionalComment) {
		this.additionalComment = additionalComment;
	}
}
