package org.ab.service;

import java.util.List;

import org.ab.dao.SubscribersDao;
import org.ab.entity.Subscriber;
import org.ab.model.InvoiceGenerationParams;
import org.ab.model.InvoiceModel;
import org.ab.model.SubscriberModel;
import org.ab.service.converter.SubscriberConverter;
import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class InvoicesService {

	@Transactional
	public void generateInvoices(final InvoiceGenerationParams generationParams) {
		// TODO Auto-generated method stub
		// 1. find contracts between first and last day of month contract activation date before first day contract end date >=last day of month
		// 2. for each contract:
		//	- wygeneruj usluge z uslug¹/uslugami z kontraktu
		// 	- dodaj oplate instalacyjna i aktywacyjna jesli nie byla pobrana.
		// 	- encja faktury w bazie powinna miec pole albo mapowanie do pola z contentem faktury w HTMLu
		
		
	}

	public List<InvoiceModel> findInvoices(final LocalDate dateFrom, final LocalDate dateTo) {
		// TODO Auto-generated method stub
		return null;
	}
}
