package com.twistlet.mudahscrape.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twistlet.mudahscrape.model.entity.House;
import com.twistlet.mudahscrape.model.repository.HouseRepository;

@Service("databaseService")
public class DatabaseServiceImpl implements DatabaseService {
	private final HouseRepository houseRepository;

	@Autowired
	public DatabaseServiceImpl(final HouseRepository houseRepository) {
		this.houseRepository = houseRepository;
	}

	@Override
	public void saveHouse(final House house) {
		houseRepository.save(house);
	}

}
