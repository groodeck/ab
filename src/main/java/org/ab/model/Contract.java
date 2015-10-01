package org.ab.model;

import java.util.List;

import org.ab.model.dictionary.ContractStatus;
import org.ab.model.dictionary.InstalationType;

import com.google.common.collect.Lists;

public class Contract {

	private String contractId;
	private String contractIdn;
	private String contractStatusIdn;
	private String contractStatusDesc;
	private String installationTypeIdn;
	private String installationTypeDesc;
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
	private String contractPackageName;

	public Contract(){
		devices = Lists.newArrayList(new Device());
		contractStatusIdn = ContractStatus.IN_PROGRESS.name();
		installationTypeIdn = InstalationType.CABLE.name();
		active = true;
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
	public String getContractActivationDate() {
		return contractActivationDate;
	}
	public String getContractEndDate() {
		return contractEndDate;
	}
	public String getContractId() {
		return contractId;
	}
	public String getContractIdn() {
		return contractIdn;
	}
	public String getContractPack() {
		return contractPack;
	}
	public String getContractPackageName() {
		return contractPackageName;
	}
	public String getContractPeriod() {
		return contractPeriod;
	}
	public String getContractSignDate() {
		return contractSignDate;
	}
	public String getContractStatusDesc() {
		return contractStatusDesc;
	}
	public String getContractStatusIdn() {
		return contractStatusIdn;
	}
	public String getContractSubscription() {
		return contractSubscription;
	}
	public List<Device> getDevices() {
		return devices;
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

	public String getInstallationTypeDesc() {
		return installationTypeDesc;
	}

	public String getInstallationTypeIdn() {
		return installationTypeIdn;
	}

	public String getUser() {
		return user;
	}

	public boolean isActive() {
		return active;
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

	public void setActive(final boolean active) {
		this.active = active;
	}

	public void setContractActivationDate(final String contractActivationDate) {
		this.contractActivationDate = contractActivationDate;
	}

	public void setContractEndDate(final String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public void setContractId(final String contractId) {
		this.contractId = contractId;
	}

	public void setContractIdn(final String contractIdn) {
		this.contractIdn = contractIdn;
	}

	public void setContractPack(final String contractPack) {
		this.contractPack = contractPack;
	}

	public void setContractPackName(final String packageName) {
		contractPackageName = packageName;
	}

	public void setContractPeriod(final String contractPeriod) {
		this.contractPeriod = contractPeriod;
	}

	public void setContractSignDate(final String contractSignDate) {
		this.contractSignDate = contractSignDate;
	}

	public void setContractStatusDesc(final String contractStatusDesc) {
		this.contractStatusDesc = contractStatusDesc;
	}

	public void setContractStatusIdn(final String contractStatus) {
		contractStatusIdn = contractStatus;
	}

	public void setContractSubscription(final String contractSubscription) {
		this.contractSubscription = contractSubscription;
	}

	public void setDevices(final List<Device> devices) {
		this.devices = devices;
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

	public void setInstallationTypeDesc(final String installationTypeDesc) {
		this.installationTypeDesc = installationTypeDesc;
	}

	public void setInstallationTypeIdn(final String installationType) {
		installationTypeIdn = installationType;
	}

	public void setUser(final String user) {
		this.user = user;
	}
}
