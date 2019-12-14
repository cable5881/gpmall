package com.gpmall.shopping.vo;

import com.gpmall.commons.result.AbstractResponse;
import lombok.Data;

/**
 * @author liqibo
 * @description TODO
 * @date 2019/11/28 15:51
 **/
@Data
public class ItemStatResponse extends AbstractResponse {

    private Long itemId;

    /**浏览记录*/
    private Long pv;

    /**销量*/
    private Long sales;

    /**评分*/
    private Float score;

}
