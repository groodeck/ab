package org.ab.model;

import java.util.List;

import org.assertj.core.util.Lists;

public class ContractPackage {
	private String packageId;
	private String packageName;
	private String clientType;
	private String clientTypeDesc;
	private String packageSubscription;

	private String activationFeeNet;
	private String activationFeeVatRate;
	private String activationFeeVat;
	private String activationFeeGross;

	private String installationFeeNet;
	private String installationFeeVatRate;
	private String installationFeeVat;
	private String installationFeeGross;

	private List<Service> services;

	public ContractPackage(){
		services = Lists.newArrayList();
	}
	public String getActivationFeeGross() {
		return activationFeeGross;
	}
	public String getActivationFeeNet() {
		return activationFeeNet;
	}
	public String getActivationFeeVat() {
		return activationFeeVat;
	}
	public String getActivationFeeVatRate() {
		return activationFeeVatRate;
	}
	public String getClientType() {
		return clientType;
	}
	public String getClientTypeDesc() {
		return clientTypeDesc;
	}
	public String getInstallationFeeGross() {
		return installationFeeGross;
	}
	public String getInstallationFeeNet() {
		return installationFeeNet;
	}
	public String getInstallationFeeVat() {
		return installationFeeVat;
	}
	public String getInstallationFeeVatRate() {
		return installationFeeVatRate;
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
	public void setActivationFeeGross(final String activationFeeGross) {
		this.activationFeeGross = activationFeeGross;
	}
	public void setActivationFeeNet(final String activationFeeNet) {
		this.activationFeeNet = activationFeeNet;
	}
	public void setActivationFeeVat(final String activationFeeVat) {
		this.activationFeeVat = activationFeeVat;
	}
	public void setActivationFeeVatRate(final String activationFeeVatRate) {
		this.activationFeeVatRate = activationFeeVatRate;
	}
	public void setClientType(final String clientType) {
		this.clientType = clientType;
	}
	public void setClientTypeDesc(final String desc) {
		clientTypeDesc = desc;
	}
	public void setInstallationFeeGross(final String installationFeeGross) {
		this.installationFeeGross = installationFeeGross;
	}
	public void setInstallationFeeNet(final String installationFeeNet) {
		this.installationFeeNet = installationFeeNet;
	}
	public void setInstallationFeeVat(final String installationFeeVat) {
		this.installationFeeVat = installationFeeVat;
	}
	public void setInstallationFeeVatRate(final String installationFeeVatRate) {
		this.installationFeeVatRate = installationFeeVatRate;
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
