package com.gpmall.shopping.bootstrap;

import com.gpmall.commons.tool.utils.NumberUtils;
import com.gpmall.shopping.dal.entitys.Item;
import com.gpmall.shopping.dal.entitys.ItemStatistic;
import com.gpmall.shopping.dal.persistence.ItemMapper;
import com.gpmall.shopping.dal.persistence.ItemStatisticMapper;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liqibo
 * @description TODO
 * @date 2019/11/27 21:03
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
public class ItemStatisticTests {

    @Resource
    private ItemStatisticMapper mapper;
    @Resource
    private ItemMapper itemMapper;

    @Test
    public void testInsert() {
        ItemStatistic itemStatistic = new ItemStatistic();
        itemStatistic.setItemId(10010L);
        itemStatistic.setPv(300L);
        itemStatistic.setSales(200L);
        itemStatistic.setScore(4.5F);
        int insert = mapper.insert(itemStatistic);
        Assert.assertEquals(1, insert);
    }

    @Test
    public void testInsertRandom() {
        List<Item> items = itemMapper.selectAll();
        for (Item item : items) {
            ItemStatistic itemStatistic = new ItemStatistic();
            itemStatistic.setItemId(item.getId());
            itemStatistic.setPv(RandomUtils.nextLong(100, 1000000));
            itemStatistic.setSales(RandomUtils.nextLong(100, itemStatistic.getPv()));
            itemStatistic.setScore(NumberUtils.toFloat(RandomUtils.nextFloat(1, 5)));
            mapper.insert(itemStatistic);
        }
    }

    public static void main(String[] args) {
        System.out.println(NumberUtils.toFloat(RandomUtils.nextFloat(1, 5)));
    }

}
