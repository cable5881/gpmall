package com.gpmall.search.repository;

import com.gpmall.search.entity.ItemDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<ItemDocument, Integer> {
}
