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

import org.ab.model.dictionary.VatRate;

@Entity
@Table(name="ContractPackage")
public class ContractPackage {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="packageId")
	private Integer packageId;

	@Column(name="packageName")
	private String packageName;

	@Column(name="packageSubscription")
	private BigDecimal packageSubscription;

	@Column(name="activationFeeNet")
	private BigDecimal activationFeeNet;

	@Enumerated(EnumType.ORDINAL)
	@Column(name="activationFeeVatRate")
	private VatRate activationFeeVatRate;

	@Column(name="activationFeeVat")
	private BigDecimal activationFeeVat;

	@Column(name="activationFeeGross")
	private BigDecimal activationFeeGross;

	@Column(name="installationFeeNet")
	private BigDecimal installationFeeNet;

	@Enumerated(EnumType.ORDINAL)
	@Column(name="installationFeeVatRate")
	private VatRate installationFeeVatRate;

	@Column(name="installationFeeVat")
	private BigDecimal installationFeeVat;

	@Column(name="installationFeeGross")
	private BigDecimal installationFeeGross;

	@OneToMany(cascade={CascadeType.ALL}, orphanRemoval=true)
	@JoinColumn(name="packageId")
	private List<Service> services;

	public Integer getPackageId() {
		return this.packageId;
	}

	public String getPackageName() {
		return this.packageName;
	}

	public BigDecimal getPackageSubscription() {
		return this.packageSubscription;
	}

	public List<Service> getServices() {
		return this.services;
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

	public BigDecimal getActivationFeeNet() {
		return this.activationFeeNet;
	}

	public void setActivationFeeNet(final BigDecimal activationFeeNet) {
		this.activationFeeNet = activationFeeNet;
	}

	public VatRate getActivationFeeVatRate() {
		return this.activationFeeVatRate;
	}

	public void setActivationFeeVatRate(final VatRate activationFeeVatRate) {
		this.activationFeeVatRate = activationFeeVatRate;
	}

	public BigDecimal getActivationFeeVat() {
		return this.activationFeeVat;
	}

	public void setActivationFeeVat(final BigDecimal activationFeeVat) {
		this.activationFeeVat = activationFeeVat;
	}

	public BigDecimal getActivationFeeGross() {
		return this.activationFeeGross;
	}

	public void setActivationFeeGross(final BigDecimal activationFeeGross) {
		this.activationFeeGross = activationFeeGross;
	}

	public BigDecimal getInstallationFeeNet() {
		return this.installationFeeNet;
	}

	public void setInstallationFeeNet(final BigDecimal installationFeeNet) {
		this.installationFeeNet = installationFeeNet;
	}

	public VatRate getInstallationFeeVatRate() {
		return this.installationFeeVatRate;
	}

	public void setInstallationFeeVatRate(final VatRate installationFeeVatRate) {
		this.installationFeeVatRate = installationFeeVatRate;
	}

	public BigDecimal getInstallationFeeVat() {
		return this.installationFeeVat;
	}

	public void setInstallationFeeVat(final BigDecimal installationFeeVat) {
		this.installationFeeVat = installationFeeVat;
	}

	public BigDecimal getInstallationFeeGross() {
		return this.installationFeeGross;
	}

	public void setInstallationFeeGross(final BigDecimal installationFeeGross) {
		this.installationFeeGross = installationFeeGross;
	}

}
