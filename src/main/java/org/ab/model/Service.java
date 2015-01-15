package org.ab.model;

public class Service {
	private String serviceId;
	private String serviceName;
	private String subscriptionGross;
	private String subscriptionNet;
	private String vatAmount;
	private String vatRate;

	public String getServiceId() {
		return this.serviceId;
	}
	public String getServiceName() {
		return this.serviceName;
	}
	public String getSubscriptionGross() {
		return this.subscriptionGross;
	}
	public String getSubscriptionNet() {
		return this.subscriptionNet;
	}
	public String getVatAmount() {
		return this.vatAmount;
	}
	public String getVatRate() {
		return this.vatRate;
	}
	public void setServiceId(final String serviceId) {
		this.serviceId = serviceId;
	}
	public void setServiceName(final String serviceName) {
		this.serviceName = serviceName;
	}
	public void setSubscriptionGross(final String subscriptionGross) {
		this.subscriptionGross = subscriptionGross;
	}
	public void setSubscriptionNet(final String subscriptionNet) {
		this.subscriptionNet = subscriptionNet;
	}
	public void setVatAmount(final String vatAmount) {
		this.vatAmount = vatAmount;
	}
	public void setVatRate(final String vatRate) {
		this.vatRate = vatRate;
	}

}
