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

import org.ab.model.dictionary.ClientType;

@Entity
@Table(name="ContractPackage")
public class ContractPackage {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="packageId")
	private Integer packageId;

	@Column(name="packageName")
	private String packageName;

	@Column(name="packageActive")
	private Boolean packageActive;

	@Enumerated(EnumType.STRING)
	@Column(name="clientType")
	private ClientType clientType;

	@Column(name="packageSubscription")
	private BigDecimal packageSubscription;

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

	@OneToMany(cascade={CascadeType.ALL}, orphanRemoval=true)
	@JoinColumn(name="packageId")
	private List<Service> services;

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

	public ClientType getClientType() {
		return clientType;
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

	public Boolean getPackageActive() {
		return packageActive;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public BigDecimal getPackageSubscription() {
		return packageSubscription;
	}

	public List<Service> getServices() {
		return services;
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

	public void setClientType(final ClientType clientType) {
		this.clientType = clientType;
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

	public void setPackageActive(final Boolean packageActive) {
		this.packageActive = packageActive;
	}

	public void setPackageId(final Integer packageId) {
		this.packageId = packageId;
	}

	public void setPackageName(final String packageName) {
		this.packageName = packageName;
	}

	public void setPackageSubscription(final BigDecimal packageSubscription) {
		this.packageSubscription = packageSubscription;
	}

	public void setServices(final List<Service> services) {
		this.services = services;
	}

}
