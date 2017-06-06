package org.ab.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.ab.dao.CityDao;
import org.ab.dao.PageInfo;
import org.ab.entity.City;
import org.ab.model.CitiesModel;
import org.ab.model.CityModel;
import org.ab.ui.ResultPage;
import org.ab.util.IdnTranslator;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

@Component
public class CityService {

	private final static Logger log = Logger.getLogger(CityService.class);

	@Autowired
	private CityDao cityDao;

	private boolean cityExists(final String newCityIdn) {
		return cityDao.getByIdn(newCityIdn) != null;
	}

	public Map<String, String> getCityDictionary(){
		final PageInfo pageInfo = CityModel.resultTableHeader.getPageInfo();
		final List<City> cities = cityDao.findAll(pageInfo).getRecords();
		final Map<String, String> results = Maps.newHashMap();
		for(final City city : cities){
			results.put(city.getCityIdn(), city.getCityDescription());
		}
		return results;
	}

	public ResultPage<CityModel> getCityList(final PageInfo pageInfo) {
		final ResultPage<City> cities = cityDao.findAll(pageInfo);
		final ImmutableList<CityModel> resultList =
				FluentIterable.from(cities.getRecords())
				.transform(new Function<City, org.ab.model.CityModel>(){

					@Override
					public org.ab.model.CityModel apply(final City arg0) {
						return new org.ab.model.CityModel(arg0.getCityIdn(), arg0.getCityDescription());
					}

				}).toList();
		return new ResultPage<CityModel>(resultList, cities.getPageNo(), cities.getPageCount());
	}

	public CityModel saveNewCity(final String newCityDesc) {
		log.info("Trying to save new city: " + newCityDesc);
		final IdnTranslator idnTranslator = new IdnTranslator();
		final String newCityIdn = idnTranslator.convertNameToIdn(newCityDesc);
		if(cityExists(newCityIdn)){
			return null;
		} else {
			cityDao.create(newCityIdn, newCityDesc);
			return new CityModel(newCityIdn, newCityDesc);
		}
	}

	@Transactional
	public void updateCities(final CitiesModel citiesModel, final String userName) {
		for(final CityModel city : citiesModel.getRecords()){
			final City entity = cityDao.getByIdn(city.getCityIdn());
			updateCityDescription(city, entity);
		}
	}

	private void updateCityDescription(final CityModel city, final City entity) {
		final String cityDescription = city.getCityDesc();
		if(entity != null && StringUtils.isNotBlank(cityDescription)){
			entity.setCityDescription(city.getCityDesc());
		}
	}
}
