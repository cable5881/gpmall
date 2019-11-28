package com.gpmall.search.services;

import com.gpmall.search.InitDataService;
import com.gpmall.search.converter.ProductConverter;
import com.gpmall.search.dal.entitys.Item;
import com.gpmall.search.dal.persistence.ItemMapper;
import com.gpmall.search.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Slf4j
@Service
public class InitDataServiceImpl implements InitDataService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    ProductConverter productConverter;

    @Override
    public void initItems() {
        List<Item> items = itemMapper.selectAll();
        items.parallelStream().forEach(item -> productRepository.save(productConverter.item2Document(item)));
    }
}
