package org.ab.model.dictionary;

import java.util.Collection;
import java.util.Map;

import org.ab.service.CityService;
import org.ab.service.ContractPackageService;
import org.ab.service.DurationService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

@Component
public class SelectValueService {

	private static final int START_YEAR = 2010;

	@Autowired
	private CityService cityService;

	@Autowired
	private ContractPackageService packageService;

	@Autowired
	private DurationService durationService;

	public Collection<?> getCorrectionDictionaries() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, ?> getInvoicesDictionaries() {
		final Map<String, Map> results = Maps.newHashMap();
		results.put("months", Month.asValueMap());
		results.put("years", getYearValueMap());
		return results;
	}

	public Map<String, ?> getPackageDictionaries() {
		final Map<String, Map> results = Maps.newHashMap();
		results.put("vatRates", VatRate.asValueMap());
		results.put("clientTypes", ClientType.asValueMap());
		return results;
	}

	public Collection<?> getPaymentDictionaries() {
		// TODO zrob to
		return null;
	}

	public Map<String, Map<String, String>> getSubscriberDictionaries() {
		final Map<String, Map<String, String>> results = Maps.newHashMap();
		results.put("clientTypes", ClientType.asValueMap());
		results.put("contractStatuses", ContractStatus.asValueMap());
		results.put("cities", cityService.getCityDictionary());
		results.put("packages", packageService.getPackageDictionary());
		results.put("contractDurations", durationService.getDurationDictionary());
		results.put("deviceTypes", DeviceType.asValueMap());
		return results;
	}

	private Map<Integer, String> getYearValueMap() {
		final LocalDate now = LocalDate.now();
		final Map<Integer, String> valueMap = Maps.newTreeMap();
		for(int year = START_YEAR; year <= now.getYear()+1; year++){
			valueMap.put(year, String.valueOf(year));
		}
		return valueMap;
	}


}
