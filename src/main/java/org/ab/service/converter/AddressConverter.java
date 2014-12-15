package org.ab.service.converter;

import java.util.List;
import java.util.Set;

import org.ab.entity.Address;
import org.ab.model.dictionary.AddressType;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Component
public class AddressConverter {

	public List<Address> convert(final org.ab.model.Address mainAddress,
			final org.ab.model.Address serviceAddress,
			final org.ab.model.Address correspondanceAddress) {
		final List<Address> addresses = Lists.newArrayList();
		if (mainAddress != null) {
			addresses.add(convert(mainAddress, AddressType.MAIN));
		}
		if (serviceAddress != null) {
			addresses.add(convert(serviceAddress, AddressType.SERVICE));
		}
		if (correspondanceAddress != null) {
			addresses.add(convert(correspondanceAddress,
					AddressType.CORRESPONDENCE));
		}
		return addresses;
	}

	private Address convert(final org.ab.model.Address address,
			final AddressType addressType) {
		return new Address(addressType, address.getCity(),
				address.getZipCode(), address.getStreet(),
				address.getHouseNo(), address.getApartamentNo());
	}

	public org.ab.model.Address convert(final org.ab.entity.Address address) {
		org.ab.model.Address result = new org.ab.model.Address();
		result.setCity(address.getCity());
		result.setStreet(address.getStreet());
		result.setHouseNo(address.getHouseNo());
		result.setZipCode(address.getZipCode());
		result.setApartamentNo(address.getApartmentNo());
		return result;
	}
}
