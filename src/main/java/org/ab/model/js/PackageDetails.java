package org.ab.model.js;

import flexjson.JSONSerializer;

public class PackageDetails {

	private String subscription;
	private String installationFeeNet;
	private String installationFeeVatRate;
	private String installationFeeVat;
	private String installationFeeGross;

	private String activationFeeNet;
	private String activationFeeVatRate;
	private String activationFeeVat;
	private String activationFeeGross;

	public String getSubscription() {
		return this.subscription;
	}
	public void setSubscription(final String subscription) {
		this.subscription = subscription;
	}
	public String serialize(){
		final JSONSerializer serializer = new JSONSerializer();
        return serializer.serialize(this);
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
}
