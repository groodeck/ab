package org.ab.service.converter;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.math.BigDecimal;
import java.util.List;

import org.ab.entity.ContractPackage;
import org.ab.model.Service;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

@Component
public class ContractPackageConverter {

	private final Function<ContractPackage, org.ab.model.ContractPackage> toContractPackageModel =
			new Function<ContractPackage, org.ab.model.ContractPackage>(){
		@Override
		public org.ab.model.ContractPackage apply(final ContractPackage entity) {
			final org.ab.model.ContractPackage model = new org.ab.model.ContractPackage();
			if(entity.getPackageId() != null){
				model.setPackageId(entity.getPackageId().toString());
			}
			model.setPackageName(entity.getPackageName());
			if(entity.getPackageSubscription() != null){
				model.setPackageSubscription(entity.getPackageSubscription().toPlainString());
			}
			if(entity.getActivationFee() != null){
				model.setActivationFee(entity.getActivationFee().toPlainString());
			}
			if(entity.getInstallationFee() != null){
				model.setInstallationFee(entity.getInstallationFee().toPlainString());
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
			model.setDisposable(entity.isDisposable());
			model.setServiceName(entity.getServiceName());
			if(entity.getSubscriptionNet() != null){
				model.setSubscriptionNet(entity.getSubscriptionNet().toPlainString());
			}
			if(entity.getVatRate() != null){
				model.setVatRate(new VatRateConverter().convert(entity.getVatRate()));
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
			if(isNotBlank(model.getSubscriptionNet())){
				entity.setSubscriptionNet(new BigDecimal(asNumber(model.getSubscriptionNet())));
			}
			entity.setVatRate(new VatRateConverter().convert(model.getVatRate()));
			if(isNotBlank(model.getVatAmount())){
				entity.setVatAmount(new BigDecimal(asNumber(model.getVatAmount())));
			}
			if(isNotBlank(model.getSubscriptionGross())){
				entity.setSubscriptionGross(new BigDecimal(asNumber(model.getSubscriptionGross())));
			}
			entity.setDisposable(model.isDisposable());
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

			if(isNotBlank(model.getPackageSubscription())){
				entity.setPackageSubscription(new BigDecimal(asNumber(model.getPackageSubscription())));
			}

			if(isNotBlank(model.getInstallationFee())){
				entity.setInstallationFee(new BigDecimal(asNumber(model.getInstallationFee())));
			}

			if(isNotBlank(model.getActivationFee())){
				entity.setActivationFee(new BigDecimal(asNumber(model.getActivationFee())));
			}

			entity.setServices(FluentIterable.from(model.getServices())
					.transform(toServiceEntity).toList());
			return entity;
		}
	};

	private String asNumber(final String numericString) {
		return numericString.replaceAll(",", ".");
	}

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

}
