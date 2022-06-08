package com.example.dubbomybatisprovider.service.Impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.api.IGoodsService;
import com.example.api.Pojo.Goods;
import com.example.api.Pojo.GoodsVo;
import com.example.api.Pojo.User;
import com.example.api.ProviderService;
import com.example.dubbomybatisprovider.mapper.GoodsMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Shawn
 * @since 2022-05-08
 */
@Slf4j
@DubboService(interfaceClass = IGoodsService.class, version = "1.0.0",timeout=1000)
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private RedisTemplate<String,String> template;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private RedisTemplate<Object,Object> redis;

    @Override
    public List<Goods> getList(){
            List<Goods> goodsList = goodsMapper.getList();
            return goodsList;
    }

    @Override
    public List<GoodsVo> listGoodsVo() {
        return goodsMapper.listGoodsVo();
    }

    @Override
    public Goods findById(Long id) {
        return goodsMapper.findById(id);
    }

    @Override
    public GoodsVo findById2(Long id) {
        return goodsMapper.findById2(id);
    }

    @Override
    public Goods getById(Long id) {
        Goods goods = findById(id);
        if(goods == null){
            System.out.println("商品不存在");
        }
        goods.setGoodsName(null);
        goods.setGoodsTitle(null);
        goods.setGoodsImg(null);
        goods.setGoodsDetail(null);
        goods.setGoodsPrice(null);
        goods.setGoodsPrice(null);
        return goods;
    }


}
