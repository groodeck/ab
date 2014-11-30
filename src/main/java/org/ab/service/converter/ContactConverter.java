package org.ab.service.converter;

import java.util.List;
import java.util.Set;

import org.ab.entity.Contact;
import org.ab.model.dictionary.ContactType;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;

@Component
public class ContactConverter {

	public Set<Contact> convert(final List<String> phones,
			final List<String> emails) {
		final Set<Contact> contacts = Sets.newHashSet();
		if (phones != null) {
			contacts.addAll(convertPhones(phones));
		}
		if (emails != null) {
			contacts.addAll(convertEmails(emails));
		}
		return contacts;
	}

	private ImmutableList<Contact> convertEmails(final List<String> emails) {
		return FluentIterable.from(emails)
				.transform(new Function<String, Contact>() {
					public Contact apply(final String email) {
						return new Contact(ContactType.EMAIL, email);
					}
				}).toList();
	}

	private ImmutableList<Contact> convertPhones(final List<String> phones) {
		return FluentIterable.from(phones)
				.transform(new Function<String, Contact>() {
					public Contact apply(final String phoneNumber) {
						return new Contact(ContactType.PHONE, phoneNumber);
					}
				}).toList();
	}
}
