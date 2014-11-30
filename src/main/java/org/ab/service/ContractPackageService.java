package org.ab.service;

import java.util.List;
import java.util.Map;

import org.ab.dao.ContractPackageDao;
import org.ab.entity.ContractPackage;
import org.ab.model.js.PackageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

@Component
public class 
ContractPackageService {

	@Autowired
	private ContractPackageDao contractPackageDao;
	
	public Map<String, String> getPackageDictionary(){
		final List<ContractPackage> packages = contractPackageDao.findAll();
		final Map<String, String> results = Maps.newHashMap();
		for(final ContractPackage contractPackage : packages){
			results.put(contractPackage.getPackageId().toString(), 
					contractPackage.getPackageName());
		}
		return results;
	}

	public PackageDetails getPackage(String packageId) {
		final ContractPackage contractPackage = contractPackageDao.getById(packageId);
		final PackageDetails packageDetails = new PackageDetails();
		packageDetails.setSubscription(contractPackage.getPackageSubscription().toPlainString());
		packageDetails.setInstallationFee(contractPackage.getInstallationFee().toPlainString());
		packageDetails.setActivationFee(contractPackage.getActivationFee().toPlainString());
		return packageDetails;
	}
}
