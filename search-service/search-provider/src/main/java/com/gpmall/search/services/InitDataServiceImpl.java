package com.gpmall.search.services;

import com.gpmall.search.InitDataService;
import com.gpmall.search.converter.ProductConverter;
import com.gpmall.search.dal.entitys.Item;
import com.gpmall.search.dal.persistence.ItemMapper;
import com.gpmall.search.entity.ItemDocument;
import com.gpmall.search.repository.ProductRepository;
import com.gpmall.shopping.IProductService;
import com.gpmall.shopping.vo.ItemStatResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Slf4j
@Service
public class InitDataServiceImpl implements InitDataService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ProductConverter productConverter;

    @Reference
    private IProductService productService;

    @Override
    public void initItems() {
        List<Item> items = itemMapper.selectAll();
        items.parallelStream().forEach(item -> {
            ItemDocument itemDocument = productConverter.item2Document(item);
            ItemStatResponse stat = productService.getItemStatById(item.getId());
            if (stat != null) {
                itemDocument.setPv(stat.getPv());
                itemDocument.setSales(stat.getSales());
                itemDocument.setScore(stat.getScore());
            }
            productRepository.save(itemDocument);
        });
    }
}
