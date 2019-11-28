package com.gpmall.shopping.dal.entitys;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author liqibo
 * @description 商品统计
 * @date 2019/11/27 20:04
 **/
@Table(name = "tb_item_stat")
@Data
public class ItemStatistic {

    @Id
    private Long itemId;

    /**浏览记录*/
    private Long pv;

    /**销量*/
    private Long sales;

    /**评分*/
    private Float score;

}
