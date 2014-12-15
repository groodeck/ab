package org.ab.model.js;

import flexjson.JSONSerializer;

public class PackageDetails {

	private String subscription;
	private String installationFee;
	private String activationFee;
	
	public String getSubscription() {
		return subscription;
	}
	public String getInstallationFee() {
		return installationFee;
	}
	public String getActivationFee() {
		return activationFee;
	}
	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}
	public void setInstallationFee(String installationFee) {
		this.installationFee = installationFee;
	}
	public void setActivationFee(String activationFee) {
		this.activationFee = activationFee;
	}
	public String serialize(){
		JSONSerializer serializer = new JSONSerializer();
        return serializer.serialize(this);
	}
}
