package org.ab.service.converter;

import java.util.List;

import org.ab.dao.ContractPackageDao;
import org.ab.dao.UserDao;
import org.ab.service.generator.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;


@Component
public class InvoiceConverter {

	@Autowired
	private ContractPackageDao packageDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private DeviceConverter deviceConverter;

	public List<org.ab.entity.Invoice> convert(final List<Invoice> invoices) {

		return FluentIterable.from(invoices).transform(new Function<Invoice, org.ab.entity.Invoice>(){

			@Override
			public org.ab.entity.Invoice apply(final Invoice input) {
				final org.ab.entity.Invoice entity = new org.ab.entity.Invoice();
				entity.setContract(input.getContract());
				entity.setInvoiceNumber(input.getInvoiceNumber());
				entity.setCreateDate(input.getCreateDate());
				entity.setReceiveDate(input.getReceiveDate());
				entity.setSettlementPeriodStart(input.getSettlementPeriodStart());
				// TODO Auto-generated method stub
				return entity;
			}

		}).toList();
	}

	private String asNumber(final String numericString) {
		return numericString.replaceAll(",", ".");
	}


}
