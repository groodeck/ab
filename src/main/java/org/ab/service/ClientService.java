package org.ab.service;

import org.ab.dao.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientService {

	@Autowired
	private ClientDao clientDao;
	
	public long getMaxClientId(){
		return clientDao.getMaxClientId();
	}
}
