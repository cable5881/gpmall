package com.gpmall.search.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @author jin
 */

@Document(indexName = "tb_item", type = "doc", shards = 1, replicas = 0)
@Data
public class ItemDocument {

    @Id
    private Integer id;

    @Field(type = FieldType.Text)
    private String image;

    @Field(type = FieldType.Long)
    private Integer status;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String sell_point;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Long)
    private Integer num;

    @Field(type = FieldType.Long)
    private Long cid;

    @Field(type = FieldType.Date)
    private Date created;

    @Field(type = FieldType.Float)
    private Double price;

    @Field(type = FieldType.Long)
    private Integer limit_num;

    @Field(type = FieldType.Date)
    private Date updated;

    /**浏览记录*/
    @Field(type = FieldType.Long)
    private Long pv;

    /**销量*/
    @Field(type = FieldType.Long)
    private Long sales;

    /**评分*/
    @Field(type = FieldType.Float)
    private Float score;

}
