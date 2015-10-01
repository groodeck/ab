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
import org.ab.model.dictionary.InstalationType;
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

	@Enumerated(EnumType.STRING)
	@Column(name="installationType")
	private InstalationType installationType;

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

	@Column(name="activationFeeNet")
	private BigDecimal activationFeeNet;

	@ManyToOne
	@JoinColumn(name="activationFeeVatRate")
	private VatRate activationFeeVatRate;

	@Column(name="activationFeeVat")
	private BigDecimal activationFeeVat;

	@Column(name="activationFeeGross")
	private BigDecimal activationFeeGross;

	@Column(name="installationFeeNet")
	private BigDecimal installationFeeNet;

	@ManyToOne
	@JoinColumn(name="installationFeeVatRate")
	private VatRate installationFeeVatRate;

	@Column(name="installationFeeVat")
	private BigDecimal installationFeeVat;

	@Column(name="installationFeeGross")
	private BigDecimal installationFeeGross;

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

	public BigDecimal getActivationFeeGross() {
		return activationFeeGross;
	}

	public BigDecimal getActivationFeeNet() {
		return activationFeeNet;
	}

	public BigDecimal getActivationFeeVat() {
		return activationFeeVat;
	}

	public VatRate getActivationFeeVatRate() {
		return activationFeeVatRate;
	}

	public LocalDate getContractActivationDate() {
		return contractActivationDate;
	}

	public LocalDate getContractEndDate() {
		return contractEndDate;
	}

	public Integer getContractId() {
		return contractId;
	}

	public String getContractIdn() {
		return contractIdn;
	}

	public ContractPackage getContractPackage() {
		return contractPackage;
	}

	public String getContractPeriod() {
		return contractPeriod;
	}

	public LocalDate getContractSignDate() {
		return contractSignDate;
	}

	public ContractStatus getContractStatus() {
		return contractStatus;
	}

	public BigDecimal getContractSubscription() {
		return contractSubscription;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public boolean getDisposableFeePaid() {
		return disposableFeePaid;
	}

	public BigDecimal getInstallationFeeGross() {
		return installationFeeGross;
	}

	public BigDecimal getInstallationFeeNet() {
		return installationFeeNet;
	}

	public BigDecimal getInstallationFeeVat() {
		return installationFeeVat;
	}

	public VatRate getInstallationFeeVatRate() {
		return installationFeeVatRate;
	}

	public InstalationType getInstallationType() {
		return installationType;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public User getUser() {
		return user;
	}

	public boolean isActive() {
		return active;
	}

	public void setActivationFeeGross(final BigDecimal activationFeeGross) {
		this.activationFeeGross = activationFeeGross;
	}

	public void setActivationFeeNet(final BigDecimal activationFeeNet) {
		this.activationFeeNet = activationFeeNet;
	}

	public void setActivationFeeVat(final BigDecimal activationFeeVat) {
		this.activationFeeVat = activationFeeVat;
	}

	public void setActivationFeeVatRate(final VatRate activationFeeVatRate) {
		this.activationFeeVatRate = activationFeeVatRate;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}

	public void setContractActivationDate(final LocalDate contractActivationDate) {
		this.contractActivationDate = contractActivationDate;
	}

	public void setContractEndDate(final LocalDate contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public void setContractId(final Integer contractId) {
		this.contractId = contractId;
	}

	public void setContractIdn(final String contractIdn) {
		this.contractIdn = contractIdn;
	}

	public void setContractPackage(final ContractPackage contractPackage) {
		this.contractPackage = contractPackage;
	}

	public void setContractPeriod(final String contractPeriod) {
		this.contractPeriod = contractPeriod;
	}

	public void setContractSignDate(final LocalDate contractSignDate) {
		this.contractSignDate = contractSignDate;
	}

	public void setContractStatus(final ContractStatus contractStatus) {
		this.contractStatus = contractStatus;
	}

	public void setContractSubscription(final BigDecimal contractSubscription) {
		this.contractSubscription = contractSubscription;
	}

	public void setDevices(final List<Device> devices) {
		this.devices = devices;
	}

	public void setDisposableFeePaid(final boolean disposableFeePaid) {
		this.disposableFeePaid = disposableFeePaid;
	}

	public void setInstallationFeeGross(final BigDecimal installationFeeGross) {
		this.installationFeeGross = installationFeeGross;
	}

	public void setInstallationFeeNet(final BigDecimal installationFeeNet) {
		this.installationFeeNet = installationFeeNet;
	}

	public void setInstallationFeeVat(final BigDecimal installationFeeVat) {
		this.installationFeeVat = installationFeeVat;
	}

	public void setInstallationFeeVatRate(final VatRate installationFeeVatRate) {
		this.installationFeeVatRate = installationFeeVatRate;
	}

	public void setInstallationType(final InstalationType installationType) {
		this.installationType = installationType;
	}

	public void setSubscriber(final Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}
