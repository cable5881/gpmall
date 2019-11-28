package com.gpmall.shopping.dal.entitys;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_item_desc")
@Data
public class ItemDesc implements Serializable {

    @Id
    private Long itemId;

    private Date created;

    private Date updated;

    private String itemDesc;

    private static final long serialVersionUID = 1L;

}