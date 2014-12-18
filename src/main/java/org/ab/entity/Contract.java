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

	@Column(name="disposableFeePaid")
	private boolean disposableFeePaid;

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
		return this.contractId;
	}

	public String getContractIdn() {
		return this.contractIdn;
	}

	public ContractStatus getContractStatus() {
		return this.contractStatus;
	}

	public LocalDate getContractSignDate() {
		return this.contractSignDate;
	}

	public LocalDate getContractActivationDate() {
		return this.contractActivationDate;
	}

	public LocalDate getContractEndDate() {
		return this.contractEndDate;
	}

	public String getContractPeriod() {
		return this.contractPeriod;
	}

	public ContractPackage getContractPackage() {
		return this.contractPackage;
	}

	public BigDecimal getContractSubscription() {
		return this.contractSubscription;
	}

	public BigDecimal getInstallationFee() {
		return this.installationFee;
	}

	public void setContractId(final Integer contractId) {
		this.contractId = contractId;
	}

	public void setContractIdn(final String contractIdn) {
		this.contractIdn = contractIdn;
	}

	public void setContractStatus(final ContractStatus contractStatus) {
		this.contractStatus = contractStatus;
	}

	public void setContractSignDate(final LocalDate contractSignDate) {
		this.contractSignDate = contractSignDate;
	}

	public void setContractActivationDate(final LocalDate contractActivationDate) {
		this.contractActivationDate = contractActivationDate;
	}

	public void setContractEndDate(final LocalDate contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public void setContractPeriod(final String contractPeriod) {
		this.contractPeriod = contractPeriod;
	}

	public void setContractPackage(final ContractPackage contractPackage) {
		this.contractPackage = contractPackage;
	}

	public void setContractSubscription(final BigDecimal contractSubscription) {
		this.contractSubscription = contractSubscription;
	}

	public void setInstallationFee(final BigDecimal installationFee) {
		this.installationFee = installationFee;
	}

	public BigDecimal getActivationFee() {
		return this.activationFee;
	}

	public void setActivationFee(final BigDecimal activationFee) {
		this.activationFee = activationFee;
	}

	public Subscriber getSubscriber() {
		return this.subscriber;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setSubscriber(final Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}

	public List<Device> getDevices() {
		return this.devices;
	}

	public void setDevices(final List<Device> devices) {
		this.devices = devices;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public boolean getDisposableFeePaid() {
		return this.disposableFeePaid;
	}

	public void setDisposableFeePaid(final boolean disposableFeePaid) {
		this.disposableFeePaid = disposableFeePaid;
	}

}
