package org.ab.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Service")
public class Service {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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

	@ManyToOne
	@JoinColumn(name="packageId", insertable=false, updatable=false, nullable=false)
	private ContractPackage contractPackage;

	public ContractPackage getContractPackage() {
		return this.contractPackage;
	}

	public Integer getServiceId() {
		return this.serviceId;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public BigDecimal getSubscriptionGross() {
		return this.subscriptionGross;
	}

	public BigDecimal getSubscriptionNet() {
		return this.subscriptionNet;
	}

	public BigDecimal getVatAmount() {
		return this.vatAmount;
	}

	public Integer getVatRate() {
		return this.vatRate;
	}

	public void setContractPackage(final ContractPackage contractPackage) {
		this.contractPackage = contractPackage;
	}

	public void setServiceId(final Integer serviceId) {
		this.serviceId = serviceId;
	}

	public void setServiceName(final String serviceName) {
		this.serviceName = serviceName;
	}

	public void setSubscriptionGross(final BigDecimal subscriptionGross) {
		this.subscriptionGross = subscriptionGross;
	}

	public void setSubscriptionNet(final BigDecimal subscriptionNet) {
		this.subscriptionNet = subscriptionNet;
	}

	public void setVatAmount(final BigDecimal vat) {
		this.vatAmount = vat;
	}

	public void setVatRate(final Integer vatRate) {
		this.vatRate = vatRate;
	}

}
