package com.ccbobe.escoreserver;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<Products, Serializable> {

    public List<Products> queryProductsByNameLike(String name);


}
