package org.ab.model;

import org.ab.model.dictionary.DeviceType;

public class Device {

	private DeviceType deviceType;
	private String serialNumber;
	private String mac;
	private String ip;
	private String deviceId;

	public String getDeviceId() {
		return deviceId;
	}
	public DeviceType getDeviceType() {
		return deviceType;
	}
	public String getIp() {
		return ip;
	}
	public String getMac() {
		return mac;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setDeviceId(final String deviceId) {
		this.deviceId = deviceId;
	}
	public void setDeviceType(final DeviceType deviceType) {
		this.deviceType = deviceType;
	}
	public void setIp(final String ip) {
		this.ip = ip;
	}
	public void setMac(final String mac) {
		this.mac = mac;
	}
	public void setSerialNumber(final String serialNumber) {
		this.serialNumber = serialNumber;
	}

}
