package org.ab.service.converter;

import org.ab.model.dictionary.VatRate;

public class VatRateConverter {

	public String convert(final Integer rate) {
		if(rate == null) {
			return VatRate.VAT_ZW.name();
		}
		switch (rate) {
		case 0:
			return VatRate.VAT_0.name();
		case 7:
			return VatRate.VAT_7.name();
		case 22:
			return VatRate.VAT_22.name();
		default:
			throw new IllegalStateException("Illegal VatRate value");
		}
	}

	public Integer convert(final String vatRate) {
		final VatRate rate = VatRate.valueOf(vatRate);
		switch (rate) {
		case VAT_0:
			return 0;
		case VAT_ZW:
			return null;
		case VAT_7:
			return 7;
		case VAT_22:
			return 22;
		default:
			throw new IllegalStateException("Illegal VatRate value");
		}
	}
}
