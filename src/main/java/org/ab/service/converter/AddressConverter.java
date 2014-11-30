package org.ab.service.converter;

import java.util.Set;

import org.ab.entity.Address;
import org.ab.model.dictionary.AddressType;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

@Component
public class AddressConverter {

	public Set<Address> convert(final org.ab.model.Address mainAddress,
			final org.ab.model.Address serviceAddress,
			final org.ab.model.Address correspondanceAddress) {
		final Set<Address> addresses = Sets.newHashSet();
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
}
