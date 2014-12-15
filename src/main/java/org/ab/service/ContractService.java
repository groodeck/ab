package org.ab.service;

import org.ab.dao.ClientDao;
import org.ab.dao.ContractDao;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContractService {

	@Autowired
	private ContractDao contractDao;
	
	public long getContractsSignCount(final LocalDate date){
		return contractDao.getContractsSignCount(date);
	}
}
