package org.ab.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	@Column(name="activationFee")
	private BigDecimal activationFee;

	@Column(name="installationFee")
	private BigDecimal installationFee;

	@OneToMany(cascade={CascadeType.ALL}, orphanRemoval=true)
	@JoinColumn(name="packageId")
	private List<Service> services;

	public BigDecimal getActivationFee() {
		return activationFee;
	}

	public BigDecimal getInstallationFee() {
		return installationFee;
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

	public void setActivationFee(final BigDecimal activationFee) {
		this.activationFee = activationFee;
	}

	public void setInstallationFee(final BigDecimal installationFee) {
		this.installationFee = installationFee;
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
