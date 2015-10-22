package org.ab.service;

import java.util.Map;

import org.ab.entity.Subscriber;
import org.ab.model.Address;
import org.ab.model.ContractPackage;
import org.ab.model.SubscriberModel;
import org.ab.service.converter.SubscriberConverter;
import org.ab.util.PropertiesReader;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.util.Maps;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;

@Component
public class SubscriberContractDetailsReader {

	@Autowired
	private SubscriberConverter subscriberConverter;

	@Autowired
	private ContractPackageService contractPackageService;

	public Map<String, String> getContractDetails(final Subscriber subscriber) {
		final SubscriberModel subscriberModel = subscriberConverter.convert(subscriber);
		final Map<String, String> results = Maps.newHashMap();
		putValueOrEmpty("<CONTRACT_NUMBER>",subscriberModel.getCurrentContract().getContractIdn(), results);
		putValueOrEmpty("<CURRENT_DATE>",getContractSignDate(subscriberModel), results);
		putValueOrEmpty("<PLACE_OF_SIGN>",getPlaceOfSign(), results);
		putValueOrEmpty("<CUSTOMER_NAME>",subscriberModel.getEffectiveName(), results);
		putValueOrEmpty("<INSTALLATION_ADDRESS>",getOneLineAddress(subscriberModel.getServiceAddress()), results);
		putValueOrEmpty("<MAIN_ADDRESS>", getOneLineAddress(subscriberModel.getMainAddress()), results);
		putValueOrEmpty("<CORRESPONDENCE_ADDRESS>",getOneLineAddress(subscriberModel.getCorrespondenceAddress()), results);
		putValueOrEmpty("<ID_NUMBER>",subscriberModel.getClientIdNumber(), results);
		putValueOrEmpty("<PESEL>",subscriberModel.getPesel(), results);
		putValueOrEmpty("<EMAIL>",Joiner.on(", ").join(subscriberModel.getEmails()), results);
		putValueOrEmpty("<PHONE_NUMBER>", Joiner.on(", ").join(subscriberModel.getPhoneNumbers()), results);
		putValueOrEmpty("<PAYMENT_ACCOUNT_NUMBER>", getPaymentAccountNumber(), results);
		putValueOrEmpty("<CONTRACT_PACKAGE>",subscriberModel.getCurrentContract().getContractPackageName(), results);
		putValueOrEmpty("<GROSS_AMOUNT>", getGrossSubscription(subscriberModel.getCurrentContract().getContractPack()), results);
		putValueOrEmpty("<INSTALLATION_FEE>",subscriberModel.getCurrentContract().getInstallationFeeGross(), results);
		return results;
	}

	private String getContractSignDate(final SubscriberModel subscriberModel) {
		final String contractSignDateStr = subscriberModel.getCurrentContract().getContractSignDate();
		final LocalDate signDate = LocalDate.parse(contractSignDateStr);
		return signDate.toString("dd-MM-yyyy");
	}

	private String getGrossSubscription(final String contractPackageId) {
		final ContractPackage contractPackage = contractPackageService.getContractPackage(contractPackageId);
		return contractPackage.getPackageSubscription();
	}

	private String getOneLineAddress(final Address address) {
		if(address == null){
			return StringUtils.EMPTY;
		} else {
			return address.getOneLineAddress();
		}
	}

	private String getPaymentAccountNumber() {
		return PropertiesReader.getProperty("company.bankAccount");
	}

	private String getPlaceOfSign() {
		return PropertiesReader.getProperty("company.city");
	}

	private void putValueOrEmpty(final String placeholder, final String replacement,
			final Map<String, String> results) {
		if(replacement == null){
			results.put(placeholder, "");
		} else {
			results.put(placeholder, replacement);
		}
	}

}
