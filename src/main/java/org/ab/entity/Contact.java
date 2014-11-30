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

import org.ab.model.dictionary.ContactType;

@Entity
@Table(name="Contact")
public class Contact {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="contactId")
	private Integer contactId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="contactType")
	private ContactType contactType;
	
	@Column(name="contact")
	private String contact;
	
	@ManyToOne
    @JoinColumn(name="subscriberId", insertable=false, updatable=false, nullable=false)
	private Subscriber subscriber;

	public Integer getContactId() {
		return contactId;
	}

	public Contact(ContactType contactType, String contact) {
		this.contactType = contactType;
		this.contact = contact;
	}

	public ContactType getContactType() {
		return contactType;
	}

	public String getContact() {
		return contact;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}
	
}
