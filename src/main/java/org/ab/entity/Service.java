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

	@Column(name="vatRate")
	private Integer vatRate;

	@Column(name="vat")
	private BigDecimal vatAmount;

	@Column(name="subscriptionNet")
	private BigDecimal subscriptionNet;

	@Column(name="subscriptionGross")
	private BigDecimal subscriptionGross;

	@Column(name="disposable")
	private boolean disposable;

	@ManyToOne
    @JoinColumn(name="packageId", insertable=false, updatable=false, nullable=false)
	private ContractPackage contractPackage;

	public Integer getServiceId() {
		return this.serviceId;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public BigDecimal getVatAmount() {
		return this.vatAmount;
	}

	public BigDecimal getSubscriptionNet() {
		return this.subscriptionNet;
	}

	public void setServiceId(final Integer serviceId) {
		this.serviceId = serviceId;
	}

	public void setServiceName(final String serviceName) {
		this.serviceName = serviceName;
	}

	public void setVatAmount(final BigDecimal vat) {
		this.vatAmount = vat;
	}

	public void setSubscriptionNet(final BigDecimal subscriptionNet) {
		this.subscriptionNet = subscriptionNet;
	}

	public ContractPackage getContractPackage() {
		return this.contractPackage;
	}

	public void setContractPackage(final ContractPackage contractPackage) {
		this.contractPackage = contractPackage;
	}

	public Integer getVatRate() {
		return this.vatRate;
	}

	public void setVatRate(final Integer vatRate) {
		this.vatRate = vatRate;
	}

	public BigDecimal getSubscriptionGross() {
		return this.subscriptionGross;
	}

	public void setSubscriptionGross(final BigDecimal subscriptionGross) {
		this.subscriptionGross = subscriptionGross;
	}

	public boolean isDisposable() {
		return this.disposable;
	}

	public void setDisposable(final boolean disposable) {
		this.disposable = disposable;
	}

}
