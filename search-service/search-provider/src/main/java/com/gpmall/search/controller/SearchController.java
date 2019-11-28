package com.gpmall.search.controller;

import com.gpmall.commons.result.ResponseData;
import com.gpmall.commons.result.ResponseUtil;
import com.gpmall.search.InitDataService;
import com.gpmall.search.ProductSearchService;
import com.gpmall.search.dto.SearchRequest;
import com.gpmall.search.dto.SearchResponse;
import com.gpmall.user.annotation.Anoymous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liqibo
 * @description 搜索
 * @date 2019/11/28 10:33
 **/
@RestController
@RequestMapping("/dev/shopping")
public class SearchController {

    @Autowired
    private ProductSearchService productSearchService;

    @Autowired
    private InitDataService initDataService;

    @Anoymous
    @PostMapping("/search")
    public ResponseData<SearchResponse> searchProduct(@RequestBody SearchRequest request) {
        SearchResponse response = productSearchService.search(request);
        return new ResponseUtil().setData(response.getData());
    }

    @Anoymous
    @GetMapping("/searchHotWord")
    public ResponseData<SearchResponse> getSearchHotWord() {
        SearchResponse searchResponse = productSearchService.hotProductKeyword();
        return new ResponseUtil().setData(searchResponse.getData());
    }

    @Anoymous
    @GetMapping("/search")
    public ResponseData search(@RequestParam(name = "key") String key) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setKeyword(key);
        searchRequest.setPageSize(5);
        searchRequest.setCurrentPage(0);
        searchRequest.setSort("updated");
        SearchResponse searchResponse = productSearchService.fuzzySearch(searchRequest);
        return new ResponseUtil().setData(searchResponse.getData());
    }

    @Anoymous
    @GetMapping("/search/init")
    public ResponseData init() {
        initDataService.initItems();
        return new ResponseUtil().setData(null);
    }

}
