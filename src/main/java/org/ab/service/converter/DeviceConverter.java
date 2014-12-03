package org.ab.service.converter;

import java.util.List;

import org.ab.entity.Device;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

@Component
public class DeviceConverter {

	public List<Device> convert(final List<org.ab.model.Device> devices) {
		return FluentIterable.from(devices).transform(new Function<org.ab.model.Device, Device>() {

			@Override
			public Device apply(final org.ab.model.Device input) {
				final Device result = new Device();
				result.setDeviceType(input.getDeviceType());
				result.setSerialNumber(input.getSerialNumber());
				result.setMac(input.getMac());
				result.setIp(input.getIp());
				return result;
			}
		}).toList();
	}

}
