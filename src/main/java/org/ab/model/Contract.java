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
	private String installationFee;
	private String activationFee;
	private List<Device> devices;
	private String user;
	private boolean active;
	
	public Contract(){
		devices = Lists.newArrayList(new Device());
		active = true;
	}
	
	public String getContractIdn() {
		return contractIdn;
	}
	public String getContractStatus() {
		return contractStatus;
	}
	public String getContractSignDate() {
		return contractSignDate;
	}
	public String getContractActivationDate() {
		return contractActivationDate;
	}
	public String getContractPeriod() {
		return contractPeriod;
	}
	public String getContractPack() {
		return contractPack;
	}
	public String getContractSubscription() {
		return contractSubscription;
	}
	public String getInstallationFee() {
		return installationFee;
	}
	public void setContractIdn(String contractIdn) {
		this.contractIdn = contractIdn;
	}
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	public void setContractSignDate(String contractSignDate) {
		this.contractSignDate = contractSignDate;
	}
	public void setContractActivationDate(String contractActivationDate) {
		this.contractActivationDate = contractActivationDate;
	}
	public void setContractPeriod(String contractPeriod) {
		this.contractPeriod = contractPeriod;
	}
	public void setContractPack(String contractPack) {
		this.contractPack = contractPack;
	}
	public void setContractSubscription(String contractSubscription) {
		this.contractSubscription = contractSubscription;
	}
	public void setInstallationFee(String installationFee) {
		this.installationFee = installationFee;
	}
	public String getContractEndDate() {
		return contractEndDate;
	}
	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getActivationFee() {
		return activationFee;
	}
	public void setActivationFee(String activationFee) {
		this.activationFee = activationFee;
	}
	public List<Device> getDevices() {
		return devices;
	}
	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
