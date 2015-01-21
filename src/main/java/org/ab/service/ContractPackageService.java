package org.ab.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.ab.dao.ContractPackageDao;
import org.ab.entity.ContractPackage;
import org.ab.model.dictionary.ClientType;
import org.ab.model.js.PackageDetails;
import org.ab.service.converter.ContractPackageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

@Component
public class ContractPackageService {

	@Autowired
	private ContractPackageConverter contractPackageConverter;

	@Autowired
	private ContractPackageDao contractPackageDao;

	@Transactional
	public List<org.ab.model.ContractPackage> getAllPackages() {
		final List<ContractPackage> all = contractPackageDao.findAll();
		return contractPackageConverter.convert(all);
	}

	@Transactional
	public org.ab.model.ContractPackage getContractPackage(final int packageId) {
		final ContractPackage contractPackage = contractPackageDao.getById(String.valueOf(packageId));
		return contractPackageConverter.convert(contractPackage);
	}

	public PackageDetails getPackage(final String packageId) {
		final ContractPackage contractPackage = contractPackageDao.getById(packageId);
		final PackageDetails packageDetails = new PackageDetails();
		packageDetails.setSubscription(contractPackage.getPackageSubscription().toPlainString());
		packageDetails.setInstallationFeeNet(contractPackage.getInstallationFeeNet().toPlainString());
		packageDetails.setInstallationFeeVatRate(contractPackage.getInstallationFeeVatRate().name());
		packageDetails.setInstallationFeeVat(contractPackage.getInstallationFeeVat().toPlainString());
		packageDetails.setInstallationFeeGross(contractPackage.getInstallationFeeGross().toPlainString());
		packageDetails.setActivationFeeNet(contractPackage.getActivationFeeNet().toPlainString());
		packageDetails.setActivationFeeVatRate(contractPackage.getActivationFeeVatRate().name());
		packageDetails.setActivationFeeVat(contractPackage.getActivationFeeVat().toPlainString());
		packageDetails.setActivationFeeGross(contractPackage.getActivationFeeGross().toPlainString());
		return packageDetails;
	}

	public Map<String, String> getPackageDictionary(){
		final List<ContractPackage> packages = contractPackageDao.findAll();
		final Map<String, String> results = Maps.newHashMap();
		for(final ContractPackage contractPackage : packages){
			results.put(contractPackage.getPackageId().toString(),
					contractPackage.getPackageName());
		}
		return results;
	}

	public Map<String, String> getPackageDictionary(final String clientTypeName) {
		final ClientType clientType = ClientType.valueOf(clientTypeName);
		final List<ContractPackage> packages = contractPackageDao.findAllOfClientType(clientType);
		final Map<String, String> results = Maps.newHashMap();
		for(final ContractPackage contractPackage : packages){
			results.put(contractPackage.getPackageId().toString(),
					contractPackage.getPackageName());
		}
		return results;
	}

	public void save(final org.ab.model.ContractPackage contractPackage, final String name) {
		final org.ab.entity.ContractPackage entity = contractPackageConverter.convert(contractPackage);
		contractPackageDao.save(entity);
	}
}
