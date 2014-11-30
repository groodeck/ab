package org.ab.controller;

import java.util.logging.Logger;

import org.ab.model.Contract;
import org.ab.model.SubscriberModel;
import org.ab.model.dictionary.ClientType;
import org.ab.model.dictionary.SelectValueService;
import org.ab.model.js.PackageDetails;
import org.ab.service.CityService;
import org.ab.service.ClientService;
import org.ab.service.ContractPackageService;
import org.ab.service.ContractService;
import org.ab.service.SubscriberService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/async")
public class AsyncController {
	private static final Logger logger = Logger.getLogger(AsyncController.class.getName());
	
	@Autowired
	private ContractPackageService packageService;
	
	@RequestMapping(value="/getPackageDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getPackageDetails(@PathVariable int id) {
		PackageDetails packageDetails = packageService.getPackage(String.valueOf(id));
        return packageDetails.serialize();
    }
}
