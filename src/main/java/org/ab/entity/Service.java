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

	@ManyToOne
	@JoinColumn(name="vatRate")
	private VatRate vatRate;

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
		return contractPackage;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public BigDecimal getSubscriptionGross() {
		return subscriptionGross;
	}

	public BigDecimal getSubscriptionNet() {
		return subscriptionNet;
	}

	public BigDecimal getVatAmount() {
		return vatAmount;
	}

	public VatRate getVatRate() {
		return vatRate;
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
		vatAmount = vat;
	}

	public void setVatRate(final VatRate vatRate) {
		this.vatRate = vatRate;
	}

}
