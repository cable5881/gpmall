package com.gpmall.search.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class ProductVo implements Serializable {

    private static final long serialVersionUID = 2763986506997467400L;

    private Long productId;

    private BigDecimal salePrice;

    private String productName;

    private String subTitle;

    private String picUrl;
}
