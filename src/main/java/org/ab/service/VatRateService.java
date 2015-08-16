package org.ab.service;

import java.util.List;
import java.util.Map;

import org.ab.dao.VatRateDao;
import org.ab.entity.VatRate;
import org.ab.model.VatRateModel;
import org.ab.util.IdnTranslator;
import org.ab.util.Translator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Maps;

@Component
public class VatRateService {

	private static final Logger log = Logger.getLogger(VatRateService.class);

	@Autowired
	private VatRateDao vatRateDao;

	public Map<String, String> getVatRateDictionary() {
		final List<VatRate> rates = vatRateDao.findAll();
		final Map<String, String> results = Maps.newHashMap();
		for(final VatRate rate : rates){
			results.put(rate.getVatRateIdn(), rate.getVatRateDesc());
		}
		return results;
	}

	public List<VatRateModel> getVatRateList() {
		final List<VatRate> rates = vatRateDao.findAll();
		return FluentIterable.from(rates).transform(new Function<VatRate, VatRateModel>(){
			@Override
			public VatRateModel apply(final VatRate input) {
				final String vatRateIdn = input.getVatRateIdn();
				final String vatRateDesc = input.getVatRateDesc();
				final Integer value = input.getValue();
				return new VatRateModel(vatRateIdn, vatRateDesc, value);
			}
		}).toList();
	}

	public VatRateModel saveNewVatRate(final String newVatRateDesc, final String value) {
		log.info("Trying to save new VatRate: " + newVatRateDesc);
		final IdnTranslator idnTranslator = new IdnTranslator();
		final String vatRateIdn = idnTranslator.convertNameToIdn(newVatRateDesc);
		final Integer vatRateValue = Translator.parseInteger(value);
		if(vatRateExists(vatRateIdn) || vatRateValue == null){
			return null;
		} else {
			vatRateDao.create(vatRateIdn, newVatRateDesc, vatRateValue);
			return new VatRateModel(vatRateIdn, newVatRateDesc, vatRateValue);
		}
	}

	private boolean vatRateExists(final String vatRateIdn) {
		return vatRateDao.getByIdn(vatRateIdn) != null;
	}

}
