package org.ab.service.converter;

import java.util.List;

import org.ab.entity.Device;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

@Component
public class DeviceConverter {

	private final Function<Device, org.ab.model.Device> entityToModel = new Function<Device, org.ab.model.Device>() {
		@Override
		public org.ab.model.Device apply(final Device entity) {
			final org.ab.model.Device model = new org.ab.model.Device();
			model.setDeviceId(entity.getDeviceId().toString());
			model.setDeviceType(entity.getDeviceType());
			model.setIp(entity.getIp());
			model.setMac(entity.getMac());
			model.setSerialNumber(entity.getSerialNumber());
			return model;
		}
	};

	private final Function<org.ab.model.Device, Device> modelToEntity = new Function<org.ab.model.Device, Device>() {
		@Override
		public Device apply(final org.ab.model.Device input) {
			final Device result = new Device();
			final String deviceId = input.getDeviceId();
			if(StringUtils.isNotBlank(deviceId)){
				result.setDeviceId(Integer.parseInt(deviceId));
			}
			result.setDeviceType(input.getDeviceType());
			result.setSerialNumber(input.getSerialNumber());
			result.setMac(input.getMac());
			result.setIp(input.getIp());
			return result;
		}
	};

	public List<Device> convert(final List<org.ab.model.Device> devices) {
		return FluentIterable.from(devices).transform(modelToEntity).toList();
	}

	public List<org.ab.model.Device> convertToModel(final List<Device> devices) {
		return FluentIterable.from(devices).transform(entityToModel).toList();
	}
}
