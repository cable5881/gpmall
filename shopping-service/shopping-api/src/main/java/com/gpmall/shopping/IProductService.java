package com.gpmall.shopping;

import com.gpmall.shopping.dto.*;
import com.gpmall.shopping.vo.ItemStatResponse;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/7/24-16:25
 * 商品信息服务接口
 * 查询所有商品，以及商品详情
 */
public interface IProductService {

    /**
     * 查看商品明细
     * @param request
     * @return
     */
    ProductDetailResponse getProductDetail(ProductDetailRequest request);

    /**
     * 查询所有商品（分页）
     * @param request
     * @return
     */
    AllProductResponse getAllProduct(AllProductRequest request);

    /**
     * 获取推荐的商品板块
     * @return
     */
    RecommendResponse getRecommendGoods();

    /**
     * @author liqibo
     * @date 2019/11/28 15:52
     * @description 根据itemId查询商品统计
     */
    ItemStatResponse getItemStatById(long id);

}
