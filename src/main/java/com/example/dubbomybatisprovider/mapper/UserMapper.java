package com.example.dubbomybatisprovider.mapper;



import com.example.api.Pojo.User;
import com.example.dubbomybatisprovider.cache.MybatisPlusRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@CacheNamespace(implementation = MybatisPlusRedisCache.class,eviction=MybatisPlusRedisCache.class)
public interface UserMapper {
    User queryById(@Param("id") Integer id);
    int insert(@Param("name")String name,@Param("age")Integer age);
}
