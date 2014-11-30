package org.ab.service.converter;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.math.BigDecimal;

import org.ab.dao.ContractPackageDao;
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
	
	public Contract convert(org.ab.model.Contract model) {
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
			entity.setContractSubscription(new BigDecimal(contractSubscription));
		}
		
		final String installationFee = model.getInstallationFee();
		if(isNotBlank(installationFee)){
			entity.setInstallationFee(new BigDecimal(installationFee));
		}
		
		final String activationFee = model.getActivationFee();
		if(isNotBlank(activationFee)){
			entity.setActivationFee(new BigDecimal(activationFee));
		}
		return entity;
	}

}
