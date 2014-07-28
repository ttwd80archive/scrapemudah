package com.twistlet.mudahscrape.model.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.twistlet.mudahscrape.model.entity.House;

public interface HouseRepository extends ElasticsearchRepository<House, String> {

}
