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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.ab.model.dictionary.ContractStatus;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Entity
@Table(name="Contract")
public class Contract {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="contractId")
	private Integer contractId;
	
	@Column(name="contractIdn")
	private String contractIdn;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="contractStatus")
	private ContractStatus contractStatus;
	
	@Column(name="contractSignDate")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate contractSignDate;
	
	@Column(name="contractActivationDate")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate contractActivationDate;
	
	@Column(name="contractEndDate")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate contractEndDate;
	
	@Column(name="contractPeriod")
	private String contractPeriod;
	
	@ManyToOne
	@JoinColumn(name="contractPackageId")
	private ContractPackage contractPackage;
	
	@Column(name="contractSubscription")
	private BigDecimal contractSubscription;
	
	@Column(name="installationFee")
	private BigDecimal installationFee;
	
	@Column(name="activationFee")
	private BigDecimal activationFee;

	@ManyToOne
    @JoinColumn(name="subscriberId", insertable=false, updatable=true, nullable=true)
	private Subscriber subscriber;
	
	@Column(name="active")
	private boolean active;
	
	@OneToMany(cascade={CascadeType.ALL}, orphanRemoval=true)
    @JoinColumn(name="contractId")
	private List<Device> devices;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	public Integer getContractId() {
		return contractId;
	}

	public String getContractIdn() {
		return contractIdn;
	}

	public ContractStatus getContractStatus() {
		return contractStatus;
	}

	public LocalDate getContractSignDate() {
		return contractSignDate;
	}

	public LocalDate getContractActivationDate() {
		return contractActivationDate;
	}

	public LocalDate getContractEndDate() {
		return contractEndDate;
	}

	public String getContractPeriod() {
		return contractPeriod;
	}

	public ContractPackage getContractPackage() {
		return contractPackage;
	}

	public BigDecimal getContractSubscription() {
		return contractSubscription;
	}

	public BigDecimal getInstallationFee() {
		return installationFee;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public void setContractIdn(String contractIdn) {
		this.contractIdn = contractIdn;
	}

	public void setContractStatus(ContractStatus contractStatus) {
		this.contractStatus = contractStatus;
	}

	public void setContractSignDate(LocalDate contractSignDate) {
		this.contractSignDate = contractSignDate;
	}

	public void setContractActivationDate(LocalDate contractActivationDate) {
		this.contractActivationDate = contractActivationDate;
	}

	public void setContractEndDate(LocalDate contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public void setContractPeriod(String contractPeriod) {
		this.contractPeriod = contractPeriod;
	}

	public void setContractPackage(ContractPackage contractPackage) {
		this.contractPackage = contractPackage;
	}

	public void setContractSubscription(BigDecimal contractSubscription) {
		this.contractSubscription = contractSubscription;
	}

	public void setInstallationFee(BigDecimal installationFee) {
		this.installationFee = installationFee;
	}

	public BigDecimal getActivationFee() {
		return activationFee;
	}

	public void setActivationFee(BigDecimal activationFee) {
		this.activationFee = activationFee;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public boolean isActive() {
		return active;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
