package org.ab.model;

import org.ab.model.dictionary.DeviceType;

public class Device {
	
	private DeviceType deviceType;
	private String serialNumber;
	private String mac;
	private String ip;
	
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
	
}
