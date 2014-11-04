package org.ab.model;

public class Contract {

	private String contractId;
	private String contractStatus;
	private String contractSignDate;
	private String contractActivationDate;
	private String contractPeriod;
	private String contractPack;
	private String contractSubscription;
	private String installationFee;
	
	public String getContractId() {
		return contractId;
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
	public void setContractId(String contractId) {
		this.contractId = contractId;
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
	
}
