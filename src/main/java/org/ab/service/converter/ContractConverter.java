package org.ab.service.converter;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.math.BigDecimal;

import org.ab.dao.ContractPackageDao;
import org.ab.dao.UserDao;
import org.ab.entity.Contract;
import org.ab.entity.ContractPackage;
import org.ab.model.dictionary.ContractStatus;
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
	private DeviceConverter deviceConverter;
	
	public Contract convert(org.ab.model.Contract model, final String userName) {
		final Contract entity = new Contract();
		
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
		
		entity.setContractPeriod(model.getContractPeriod());
		
		final String contractSubscription = model.getContractSubscription();
		if(isNotBlank(contractSubscription)){
			entity.setContractSubscription(new BigDecimal(asNumber(contractSubscription)));
		}
		
		final String installationFee = model.getInstallationFee();
		if(isNotBlank(installationFee)){
			entity.setInstallationFee(new BigDecimal(asNumber(installationFee)));
		}
		
		final String activationFee = model.getActivationFee();
		if(isNotBlank(activationFee)){
			entity.setActivationFee(new BigDecimal(asNumber(activationFee)));
		}
		
		entity.setDevices(deviceConverter.convert(model.getDevices()));
		
		entity.setActive(model.isActive());
		
		entity.setUser(userDao.findByName(userName));
		
		return entity;
	}

	private String asNumber(final String numericString) {
		return numericString.replaceAll(",", ".");
	}

	public org.ab.model.Contract convert(final Contract entity) {
		org.ab.model.Contract model = new org.ab.model.Contract();
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
		}
		if(entity.getActivationFee() != null){
			model.setActivationFee(entity.getActivationFee().toPlainString());
		}
		if(entity.getInstallationFee() != null){
			model.setInstallationFee(entity.getInstallationFee().toPlainString());
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

}
