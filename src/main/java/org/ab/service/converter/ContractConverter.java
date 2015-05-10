package org.ab.service.converter;

import static org.ab.util.Translator.toAmount;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.math.BigDecimal;

import org.ab.dao.ContractPackageDao;
import org.ab.dao.SubscriberDao;
import org.ab.dao.UserDao;
import org.ab.entity.Contract;
import org.ab.entity.ContractPackage;
import org.ab.entity.Subscriber;
import org.ab.model.dictionary.ContractStatus;
import org.ab.model.dictionary.VatRate;
import org.ab.util.Translator;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ContractConverter {

	@Autowired
	private ContractPackageDao packageDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private SubscriberDao subscriberDao;

	@Autowired
	private DeviceConverter deviceConverter;

	private String asNumber(final String numericString) {
		return numericString.replaceAll(",", ".");
	}

	public org.ab.model.Contract convert(final Contract entity) {
		final org.ab.model.Contract model = new org.ab.model.Contract();
		if(entity.getContractId() != null){
			model.setContractId(entity.getContractId().toString());
		}
		model.setContractIdn(entity.getContractIdn());
		if(entity.getContractStatus() != null){
			model.setContractStatus(entity.getContractStatus().name());
		}
		if(entity.getContractSignDate() != null){
			model.setContractSignDate(entity.getContractSignDate().toString());
		}
		if(entity.getContractEndDate() != null){
			model.setContractEndDate(entity.getContractEndDate().toString());
		}
		if(entity.getContractActivationDate() != null){
			model.setContractActivationDate(entity.getContractActivationDate().toString());
		}
		model.setContractPeriod(entity.getContractPeriod());
		if(entity.getContractPackage() != null){
			model.setContractPack(entity.getContractPackage().getPackageId().toString());
			model.setContractPackName(entity.getContractPackage().getPackageName());
		}
		if(entity.getActivationFeeNet() != null){
			model.setActivationFeeNet(entity.getActivationFeeNet().toPlainString());
		}
		if(entity.getActivationFeeVatRate() != null){
			model.setActivationFeeVatRate(entity.getActivationFeeVatRate().name());
		}
		if(entity.getActivationFeeVat() != null){
			model.setActivationFeeVat(entity.getActivationFeeVat().toPlainString());
		}
		if(entity.getActivationFeeGross() != null){
			model.setActivationFeeGross(entity.getActivationFeeGross().toPlainString());
		}

		if(entity.getInstallationFeeNet() != null){
			model.setInstallationFeeNet(entity.getInstallationFeeNet().toPlainString());
		}
		if(entity.getInstallationFeeVatRate() != null){
			model.setInstallationFeeVatRate(entity.getInstallationFeeVatRate().name());
		}
		if(entity.getInstallationFeeVat() != null){
			model.setInstallationFeeVat(entity.getInstallationFeeVat().toPlainString());
		}
		if(entity.getInstallationFeeGross() != null){
			model.setInstallationFeeGross(entity.getInstallationFeeGross().toPlainString());
		}
		if(entity.getContractSubscription() != null){
			model.setContractSubscription(entity.getContractSubscription().toPlainString());
		}
		if(entity.getUser() != null){
			model.setUser(entity.getUser().getName());
		}
		model.setDevices(deviceConverter.convertToModel(entity.getDevices()));

		return model;
	}

	public Contract convert(final org.ab.model.Contract model, final String subscriberId, final String userName) {
		final Contract entity = new Contract();

		final String contractId = model.getContractId();
		if(isNotBlank(contractId)){
			entity.setContractId(Translator.parseInt(contractId));
		}

		entity.setContractIdn(model.getContractIdn());

		final String contractSignDate = model.getContractSignDate();
		if(isNotBlank(contractSignDate)){
			entity.setContractSignDate(LocalDate.parse(contractSignDate));
		}

		final String contractActivationDate = model.getContractActivationDate();
		if(isNotBlank(contractActivationDate)){
			entity.setContractActivationDate(LocalDate.parse(contractActivationDate));
		}

		final String contractEndDate = model.getContractEndDate();
		if(isNotBlank(contractEndDate)){
			entity.setContractEndDate(LocalDate.parse(contractEndDate));
		}

		entity.setContractStatus(ContractStatus.valueOf(model.getContractStatus()));

		final String contractPack = model.getContractPack();
		if(isNotBlank(contractPack)){
			final ContractPackage contractPackage = packageDao.getById(contractPack);
			entity.setContractPackage(contractPackage);
		}

		if(isNotBlank(subscriberId)){
			final Subscriber subscriber = subscriberDao.getSubscriber(Integer.parseInt(subscriberId));
			entity.setSubscriber(subscriber);
		}

		entity.setContractPeriod(model.getContractPeriod());

		final String contractSubscription = model.getContractSubscription();
		if(isNotBlank(contractSubscription)){
			entity.setContractSubscription(new BigDecimal(asNumber(contractSubscription)));
		}

		entity.setActivationFeeNet(toAmount(model.getActivationFeeNet()));
		entity.setActivationFeeVatRate(VatRate.valueOf(model.getActivationFeeVatRate()));
		entity.setActivationFeeVat(toAmount(model.getActivationFeeVat()));
		entity.setActivationFeeGross(toAmount(model.getActivationFeeGross()));

		entity.setInstallationFeeNet(toAmount(model.getInstallationFeeNet()));
		entity.setInstallationFeeVatRate(VatRate.valueOf(model.getInstallationFeeVatRate()));
		entity.setInstallationFeeVat(toAmount(model.getInstallationFeeVat()));
		entity.setInstallationFeeGross(toAmount(model.getInstallationFeeGross()));

		entity.setDevices(deviceConverter.convert(model.getDevices()));

		entity.setActive(model.isActive());

		entity.setUser(userDao.findByName(userName));

		return entity;
	}

}
