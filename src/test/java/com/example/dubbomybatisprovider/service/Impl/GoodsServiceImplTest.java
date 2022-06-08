package com.example.dubbomybatisprovider.service.Impl;

import com.example.api.IGoodsService;
import com.example.api.Pojo.Goods;
import com.example.dubbomybatisprovider.DubboMybatisProviderApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DubboMybatisProviderApplication.class)
@SpringBootTest
@Slf4j
class GoodsServiceImplTest {
    @Autowired
    private IGoodsService iGoodsService;

    @Test
    void getlist() {
        List<Goods> list = iGoodsService.getList();
        System.err.println("count"+list.size());
        for(Goods item:list){
            System.err.println(item);
        }
    }

}