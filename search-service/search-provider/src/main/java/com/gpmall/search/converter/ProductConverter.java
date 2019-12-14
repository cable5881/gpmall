package com.gpmall.search.converter;

import com.gpmall.search.dal.entitys.Item;
import com.gpmall.search.vo.ProductVo;
import com.gpmall.search.entity.ItemDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductConverter {

    @Mappings({
            @Mapping(source = "id", target = "productId"),
            @Mapping(source = "title", target = "productName"),
            @Mapping(source = "price", target = "salePrice"),
            @Mapping(source = "sell_point", target = "subTitle"),
            @Mapping(source = "image", target = "picUrl")
    })
    ProductVo item2Dto(ItemDocument item);

    List<ProductVo> items2Dto(List<ItemDocument> items);

    @Mappings({
            @Mapping(source = "sellPoint", target = "sell_point"),
            @Mapping(source = "limitNum", target = "limit_num")
    })
    ItemDocument item2Document(Item item);
}
