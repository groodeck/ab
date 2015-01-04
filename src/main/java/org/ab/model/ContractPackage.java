package org.ab.model;

import java.util.List;

import org.assertj.core.util.Lists;

public class ContractPackage {
	private String packageId;
	private String packageName;
	private String packageSubscription;
	private String activationFee;
	private String installationFee;
	private List<Service> services;

	public ContractPackage(){
		services = Lists.newArrayList();
	}

	public String getActivationFee() {
		return activationFee;
	}
	public String getInstallationFee() {
		return installationFee;
	}
	public String getPackageId() {
		return packageId;
	}
	public String getPackageName() {
		return packageName;
	}
	public String getPackageSubscription() {
		return packageSubscription;
	}
	public List<Service> getServices() {
		return services;
	}
	public void setActivationFee(final String activationFee) {
		this.activationFee = activationFee;
	}
	public void setInstallationFee(final String installationFee) {
		this.installationFee = installationFee;
	}
	public void setPackageId(final String packageId) {
		this.packageId = packageId;
	}
	public void setPackageName(final String packageName) {
		this.packageName = packageName;
	}
	public void setPackageSubscription(final String packageSubscription) {
		this.packageSubscription = packageSubscription;
	}
	public void setServices(final List<Service> services) {
		this.services = services;
	}

}
