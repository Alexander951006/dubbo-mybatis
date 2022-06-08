package com.example.dubbomybatisprovider.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.api.Pojo.Goods;
import com.example.api.Pojo.GoodsVo;
import com.example.dubbomybatisprovider.cache.MybatisPlusRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    List<Goods> getList();
    Goods findById(@Param("id") Long id);
    Integer updateNumById(@Param("id") Long id);
    List<GoodsVo> listGoodsVo();
    GoodsVo findById2(@Param("id") Long id);



}
