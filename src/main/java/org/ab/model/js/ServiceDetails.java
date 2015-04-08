package org.ab.model.js;

import flexjson.JSONSerializer;

public class ServiceDetails {

	private String serviceId;
	private String serviceName;
	private String netPrice;
	private String vatRate;

	public ServiceDetails(final String serviceId, final String serviceName, final String netPrice, final String vatRate){
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.netPrice = netPrice;
		this.vatRate = vatRate;
	}

	public String getNetPrice() {
		return netPrice;
	}

	public String getServiceId() {
		return serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getVatRate() {
		return vatRate;
	}

	public String serialize(){
		final JSONSerializer serializer = new JSONSerializer();
		return serializer.serialize(this);
	}

	public void setNetPrice(final String netPrice) {
		this.netPrice = netPrice;
	}

	public void setServiceId(final String serviceId) {
		this.serviceId = serviceId;
	}

	public void setServiceName(final String serviceName) {
		this.serviceName = serviceName;
	}

	public void setVatRate(final String vatRate) {
		this.vatRate = vatRate;
	}
}
