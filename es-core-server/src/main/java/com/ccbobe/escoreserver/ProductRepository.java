package com.ccbobe.escoreserver;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

public interface ProductRepository extends ElasticsearchRepository<Products, Serializable> {
}
