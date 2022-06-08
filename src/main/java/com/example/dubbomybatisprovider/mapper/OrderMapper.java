package com.example.dubbomybatisprovider.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.api.Pojo.Goods;
import com.example.api.Pojo.Order;
import com.example.dubbomybatisprovider.cache.MybatisPlusRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Shawn
 * @since 2022-05-08
 */
@Repository
@CacheNamespace(implementation = MybatisPlusRedisCache.class,eviction=MybatisPlusRedisCache.class)
public interface OrderMapper extends BaseMapper<Order> {
    Integer insertOrder(Order order);
    Order getOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

}
