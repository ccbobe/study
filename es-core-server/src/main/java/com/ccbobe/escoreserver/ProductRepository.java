package com.ccbobe.escoreserver;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<Products, Serializable> {

    public List<Products> queryProductsByNameLike(String name);


    public Page<Products> findAllByTag(Pageable pageable);

    public Page<Products> findAllByName(Pageable pageable);

    public Page<Process> queryProductsByName(String name);

}
