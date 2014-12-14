package org.ab.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Service")
public class Service {

	@Id
	@Column(name="serviceId")
	private Integer serviceId;
	
	@Column(name="serviceName")
	private String serviceName;
	
	@Column(name="vat")
	private Integer vat;
	
	@Column(name="subscriptionNet")
	private BigDecimal subscriptionNet;

	@ManyToOne
    @JoinColumn(name="packageId", insertable=false, updatable=false, nullable=false)
	private ContractPackage contractPackage;
	
	public Integer getServiceId() {
		return serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public Integer getVat() {
		return vat;
	}

	public BigDecimal getSubscriptionNet() {
		return subscriptionNet;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setVat(Integer vat) {
		this.vat = vat;
	}

	public void setSubscriptionNet(BigDecimal subscriptionNet) {
		this.subscriptionNet = subscriptionNet;
	}

	public ContractPackage getContractPackage() {
		return contractPackage;
	}

	public void setContractPackage(final ContractPackage contractPackage) {
		this.contractPackage = contractPackage;
	}
	
}
