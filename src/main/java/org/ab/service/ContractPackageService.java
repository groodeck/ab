package org.ab.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.ab.dao.ContractPackageDao;
import org.ab.dao.PageInfo;
import org.ab.dao.ServiceDao;
import org.ab.entity.ContractPackage;
import org.ab.entity.Service;
import org.ab.model.dictionary.ClientType;
import org.ab.model.js.PackageDetails;
import org.ab.model.js.ServiceDetails;
import org.ab.service.converter.ContractPackageConverter;
import org.ab.ui.ResultPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

@Component
public class ContractPackageService {

	private static final boolean ACTIVE_ONLY = true;

	@Autowired
	private ContractPackageConverter contractPackageConverter;

	@Autowired
	private ContractPackageDao contractPackageDao;

	@Autowired
	private ServiceDao serviceDao;

	@Transactional
	public ResultPage<org.ab.model.ContractPackage> getAllPackages(final boolean showInactive, final PageInfo pageInfo) {
		final ResultPage<ContractPackage> results = contractPackageDao.findAll(pageInfo, !showInactive);
		return contractPackageConverter.convert(results);
	}

	@Transactional
	public org.ab.model.ContractPackage getContractPackage(final String packageId) {
		final ContractPackage contractPackage = contractPackageDao.getById(packageId);
		return contractPackageConverter.convert(contractPackage);
	}

	public PackageDetails getPackage(final String packageId) {
		final ContractPackage contractPackage = contractPackageDao.getById(packageId);
		final PackageDetails packageDetails = new PackageDetails();
		packageDetails.setSubscription(contractPackage.getPackageSubscription().toPlainString());
		packageDetails.setInstallationFeeNet(contractPackage.getInstallationFeeNet().toPlainString());
		packageDetails.setInstallationFeeVatRate(contractPackage.getInstallationFeeVatRate().getVatRateIdn());
		packageDetails.setInstallationFeeVat(contractPackage.getInstallationFeeVat().toPlainString());
		packageDetails.setInstallationFeeGross(contractPackage.getInstallationFeeGross().toPlainString());
		packageDetails.setActivationFeeNet(contractPackage.getActivationFeeNet().toPlainString());
		packageDetails.setActivationFeeVatRate(contractPackage.getActivationFeeVatRate().getVatRateIdn());
		packageDetails.setActivationFeeVat(contractPackage.getActivationFeeVat().toPlainString());
		packageDetails.setActivationFeeGross(contractPackage.getActivationFeeGross().toPlainString());
		return packageDetails;
	}

	public Map<String, String> getPackageDictionary(){
		final PageInfo pageInfo = org.ab.model.ContractPackage.resultTableHeader.getPageInfo();
		final ResultPage<ContractPackage> packages = contractPackageDao.findAll(pageInfo, ACTIVE_ONLY);
		final Map<String, String> results = Maps.newHashMap();
		for(final ContractPackage contractPackage : packages.getRecords()){
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

	public Map<String, String> getPackageServicesDictionary(final String subscriberIdn){
		final List<Service> services = serviceDao.findAllForSubscriber(subscriberIdn);
		final Map<String, String> results = Maps.newHashMap();
		for(final Service service : services){
			results.put(service.getServiceId().toString(), service.getServiceName());
		}
		return results;
	}

	public ServiceDetails getService(final String id) {
		final Service service = serviceDao.getById(id);
		return new ServiceDetails(id, service.getServiceName(),
				service.getSubscriptionNet().toPlainString(), service.getVatRate().toString());
	}

	public void save(final org.ab.model.ContractPackage contractPackage, final String name) {
		final org.ab.entity.ContractPackage entity = contractPackageConverter.convert(contractPackage);
		contractPackageDao.save(entity);
	}
}
