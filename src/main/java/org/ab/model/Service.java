package org.ab.model;

public class Service {
	private boolean disposable;
	private String serviceId;
	private String serviceName;
	private String subscriptionGross;
	private String subscriptionNet;
	private String vatAmount;
	private String vatRate;

	public String getServiceId() {
		return serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public String getSubscriptionGross() {
		return subscriptionGross;
	}
	public String getSubscriptionNet() {
		return subscriptionNet;
	}
	public String getVatAmount() {
		return vatAmount;
	}
	public String getVatRate() {
		return vatRate;
	}
	public boolean isDisposable() {
		return disposable;
	}
	public void setDisposable(final boolean disposable) {
		this.disposable = disposable;
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
