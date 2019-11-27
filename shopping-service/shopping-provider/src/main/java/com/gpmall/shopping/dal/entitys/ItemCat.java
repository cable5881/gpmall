package com.gpmall.shopping.dal.entitys;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_item_cat")
@Data
public class ItemCat implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private Long parentId;

    private String name;

    private Integer status;

    private Integer sortOrder;

    private Boolean isParent;

    private String icon;

    private String remark;

    private Date created;

    private Date updated;

    private static final long serialVersionUID = 1L;

}