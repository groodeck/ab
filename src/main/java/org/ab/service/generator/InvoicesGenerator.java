package org.ab.service.generator;

import java.util.List;

import org.ab.entity.Contract;
import org.ab.entity.ContractPackage;
import org.ab.entity.Service;
import org.springframework.stereotype.Component;

@Component
public class InvoicesGenerator {
	
	// 2. for each contract:
	//	- wygeneruj fakturê z uslug¹/uslugami z kontraktu
	// 	- dodaj oplate instalacyjna i aktywacyjna jesli nie byla pobrana.
	// 	- encja faktury w bazie powinna miec pole albo mapowanie do pola z contentem faktury w HTMLu
	public List<Invoice> generateInvoices(final List<Contract> contracts) {
		for(final Contract contract : contracts){
			final ContractPackage contractPackage = contract.getContractPackage();
			final List<Service> services = contractPackage.getServices();
			for(final Service service : services){
				service.getServiceName();
				service.getVat();
				service.getSubscriptionNet();
				final Invoice invoice = new Invoice();
			}
		}
		return null;
	}
	
}