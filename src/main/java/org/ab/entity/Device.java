package org.ab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.ab.model.dictionary.DeviceType;

@Entity
@Table(name="Device")
public class Device {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="deviceId")
	private Integer deviceId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="deviceType")
	private DeviceType deviceType;
	
	@Column(name="serialNumber")
	private String serialNumber;
	
	@Column(name="mac")
	private String mac;
	
	@Column(name="ip")
	private String ip;
	
	@ManyToOne
    @JoinColumn(name="contractId", insertable=false, updatable=false, nullable=false)
	private Contract contract;

	public Integer getDeviceId() {
		return deviceId;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public String getMac() {
		return mac;
	}

	public String getIp() {
		return ip;
	}

	public Contract getContract() {
		return contract;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}


}
