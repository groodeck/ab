package org.ab.service.converter;

import static org.ab.util.Translator.toAmount;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.List;

import org.ab.dao.VatRateDao;
import org.ab.entity.ContractPackage;
import org.ab.entity.VatRate;
import org.ab.model.Service;
import org.ab.model.dictionary.ClientType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

@Component
public class ContractPackageConverter {

	@Autowired
	private VatRateDao vatRateDao;

	private final Function<ContractPackage, org.ab.model.ContractPackage> toContractPackageModel =
			new Function<ContractPackage, org.ab.model.ContractPackage>(){
		@Override
		public org.ab.model.ContractPackage apply(final ContractPackage entity) {
			final org.ab.model.ContractPackage model = new org.ab.model.ContractPackage();
			if(entity.getPackageId() != null){
				model.setPackageId(entity.getPackageId().toString());
			}
			model.setPackageName(entity.getPackageName());
			model.setClientType(entity.getClientType().name());
			model.setClientTypeDesc(entity.getClientType().getDesc());
			model.setPackageActive(entity.getPackageActive());
			if(entity.getPackageSubscription() != null){
				model.setPackageSubscription(entity.getPackageSubscription().toPlainString());
			}

			if(entity.getActivationFeeNet() != null){
				model.setActivationFeeNet(entity.getActivationFeeNet().toPlainString());
			}
			if(entity.getActivationFeeVatRate() != null){
				model.setActivationFeeVatRate(entity.getActivationFeeVatRate().getVatRateIdn());
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
				model.setInstallationFeeVatRate(entity.getInstallationFeeVatRate().getVatRateIdn());
			}
			if(entity.getInstallationFeeVat() != null){
				model.setInstallationFeeVat(entity.getInstallationFeeVat().toPlainString());
			}
			if(entity.getInstallationFeeGross() != null){
				model.setInstallationFeeGross(entity.getInstallationFeeGross().toPlainString());
			}

			model.setServices(FluentIterable.from(entity.getServices()).transform(toServiceModel).toList());

			return model;
		}
	};

	private final Function<org.ab.entity.Service, Service> toServiceModel = new Function<org.ab.entity.Service, Service>(){
		@Override
		public Service apply(final org.ab.entity.Service entity) {
			final Service model = new Service();
			if(entity.getServiceId() != null){
				model.setServiceId(entity.getServiceId().toString());
			}
			model.setServiceName(entity.getServiceName());
			if(entity.getSubscriptionNet() != null){
				model.setSubscriptionNet(entity.getSubscriptionNet().toPlainString());
			}
			if(entity.getVatRate() != null){
				model.setVatRate(entity.getVatRate().getVatRateIdn());
			}
			if(entity.getVatAmount() != null){
				model.setVatAmount(entity.getVatAmount().toPlainString());
			}
			if(entity.getSubscriptionGross() != null){
				model.setSubscriptionGross(entity.getSubscriptionGross().toPlainString());
			}
			return model;
		}

	};

	private final Function<org.ab.model.Service, org.ab.entity.Service> toServiceEntity =
			new Function<org.ab.model.Service, org.ab.entity.Service>(){
		@Override
		public org.ab.entity.Service apply(final Service model) {
			final org.ab.entity.Service entity = new org.ab.entity.Service();
			entity.setServiceName(model.getServiceName());
			entity.setSubscriptionNet(toAmount(model.getSubscriptionNet()));
			entity.setVatRate(getVatRate(model.getVatRate()));
			entity.setVatAmount(toAmount(model.getVatAmount()));
			entity.setSubscriptionGross(toAmount(model.getSubscriptionGross()));
			return entity;
		}
	};

	private final Function<org.ab.model.ContractPackage, ContractPackage> toPackageEntity =
			new Function<org.ab.model.ContractPackage, ContractPackage>(){
		@Override
		public ContractPackage apply(final org.ab.model.ContractPackage model) {
			final ContractPackage entity = new ContractPackage();
			if(StringUtils.isNotBlank(model.getPackageId())){
				entity.setPackageId(Integer.valueOf(model.getPackageId()));
			}
			entity.setPackageName(model.getPackageName());
			entity.setPackageActive(model.getPackageActive());
			if(isNotBlank(model.getClientType())){
				final ClientType clientType = ClientType.valueOf(model.getClientType());
				entity.setClientType(clientType);
			}
			entity.setPackageSubscription(toAmount(model.getPackageSubscription()));

			entity.setActivationFeeNet(toAmount(model.getActivationFeeNet()));
			entity.setActivationFeeVatRate(getVatRate(model.getActivationFeeVatRate()));
			entity.setActivationFeeVat(toAmount(model.getActivationFeeVat()));
			entity.setActivationFeeGross(toAmount(model.getActivationFeeGross()));

			entity.setInstallationFeeNet(toAmount(model.getInstallationFeeNet()));
			entity.setInstallationFeeVatRate(getVatRate(model.getInstallationFeeVatRate()));
			entity.setInstallationFeeVat(toAmount(model.getInstallationFeeVat()));
			entity.setInstallationFeeGross(toAmount(model.getInstallationFeeGross()));

			entity.setServices(FluentIterable.from(model.getServices())
					.transform(toServiceEntity).toList());
			return entity;
		}

	};

	public org.ab.model.ContractPackage convert(final ContractPackage contractPackage) {
		return toContractPackageModel.apply(contractPackage);
	}

	public List<org.ab.model.ContractPackage> convert(final List<ContractPackage> entities) {
		return FluentIterable.from(entities)
				.transform(toContractPackageModel).toList();
	}

	public ContractPackage convert(final org.ab.model.ContractPackage model) {
		return toPackageEntity.apply(model);
	}

	private VatRate getVatRate(final String activationFeeVatRate) {
		return vatRateDao.getByIdn(activationFeeVatRate);
	}

}
