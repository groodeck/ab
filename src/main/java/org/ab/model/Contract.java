package org.ab.model;

import java.util.List;

import com.google.common.collect.Lists;

public class Contract {

	private String contractId;
	private String contractIdn;
	private String contractStatus;
	private String contractSignDate;
	private String contractActivationDate;
	private String contractEndDate;
	private String contractPeriod;
	private String contractPack;
	private String contractSubscription;
	private String activationFeeNet;
	private String activationFeeVatRate;
	private String activationFeeVat;
	private String activationFeeGross;
	private String installationFeeNet;
	private String installationFeeVatRate;
	private String installationFeeVat;
	private String installationFeeGross;
	private List<Device> devices;
	private String user;
	private boolean active;

	public Contract(){
		this.devices = Lists.newArrayList(new Device());
		this.active = true;
	}

	public String getContractIdn() {
		return this.contractIdn;
	}
	public String getContractStatus() {
		return this.contractStatus;
	}
	public String getContractSignDate() {
		return this.contractSignDate;
	}
	public String getContractActivationDate() {
		return this.contractActivationDate;
	}
	public String getContractPeriod() {
		return this.contractPeriod;
	}
	public String getContractPack() {
		return this.contractPack;
	}
	public String getContractSubscription() {
		return this.contractSubscription;
	}
	public void setContractIdn(final String contractIdn) {
		this.contractIdn = contractIdn;
	}
	public void setContractStatus(final String contractStatus) {
		this.contractStatus = contractStatus;
	}
	public void setContractSignDate(final String contractSignDate) {
		this.contractSignDate = contractSignDate;
	}
	public void setContractActivationDate(final String contractActivationDate) {
		this.contractActivationDate = contractActivationDate;
	}
	public void setContractPeriod(final String contractPeriod) {
		this.contractPeriod = contractPeriod;
	}
	public void setContractPack(final String contractPack) {
		this.contractPack = contractPack;
	}
	public void setContractSubscription(final String contractSubscription) {
		this.contractSubscription = contractSubscription;
	}
	public String getContractEndDate() {
		return this.contractEndDate;
	}
	public void setContractEndDate(final String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	public String getContractId() {
		return this.contractId;
	}
	public void setContractId(final String contractId) {
		this.contractId = contractId;
	}
	public List<Device> getDevices() {
		return this.devices;
	}
	public void setDevices(final List<Device> devices) {
		this.devices = devices;
	}

	public void setUser(final String user) {
		this.user = user;
	}

	public String getUser() {
		return this.user;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}

	public String getActivationFeeNet() {
		return this.activationFeeNet;
	}

	public void setActivationFeeNet(final String activationFeeNet) {
		this.activationFeeNet = activationFeeNet;
	}

	public String getActivationFeeVatRate() {
		return this.activationFeeVatRate;
	}

	public void setActivationFeeVatRate(final String activationFeeVatRate) {
		this.activationFeeVatRate = activationFeeVatRate;
	}

	public String getActivationFeeVat() {
		return this.activationFeeVat;
	}

	public void setActivationFeeVat(final String activationFeeVat) {
		this.activationFeeVat = activationFeeVat;
	}

	public String getActivationFeeGross() {
		return this.activationFeeGross;
	}

	public void setActivationFeeGross(final String activationFeeGross) {
		this.activationFeeGross = activationFeeGross;
	}

	public String getInstallationFeeNet() {
		return this.installationFeeNet;
	}

	public void setInstallationFeeNet(final String installationFeeNet) {
		this.installationFeeNet = installationFeeNet;
	}

	public String getInstallationFeeVatRate() {
		return this.installationFeeVatRate;
	}

	public void setInstallationFeeVatRate(final String installationFeeVatRate) {
		this.installationFeeVatRate = installationFeeVatRate;
	}

	public String getInstallationFeeVat() {
		return this.installationFeeVat;
	}

	public void setInstallationFeeVat(final String installationFeeVat) {
		this.installationFeeVat = installationFeeVat;
	}

	public String getInstallationFeeGross() {
		return this.installationFeeGross;
	}

	public void setInstallationFeeGross(final String installationFeeGross) {
		this.installationFeeGross = installationFeeGross;
	}
}
