package com.example.dubbomybatisprovider.service.Impl;

import com.example.api.Pojo.LoginVo;
import com.example.api.ProviderService;
import com.example.api.exception.GlobalException;

import com.example.api.result.CodeMsg;
import com.example.api.util.MD5Util;
import com.example.api.util.UUIDutil;
import com.example.dubbomybatisprovider.mapper.UserMapper;
import com.example.api.Pojo.User;


import com.example.dubbomybatisprovider.redis.CookieUtils;
import com.example.dubbomybatisprovider.redis.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@DubboService(interfaceClass = ProviderService.class, version = "1.0.0",timeout=1000)
public class ProviderServiceImpl implements ProviderService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper mapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public User queryById(Integer id) {
            User user = mapper.queryById(id);


        return user;
    }
    @Override
    public String doLogin(LoginVo loginVo) {
        Integer id = loginVo.getId();
        String passWord = loginVo.getPassWord();
        User user = queryById(id);
        if (user == null) {
            throw new GlobalException(CodeMsg.USER_NOTEXISTS);
        }
        if(!MD5Util.formPassToDbPass(passWord,user.getSalt()).equals(user.getPassword())){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        String tokenNum = UUIDutil.getUUID();
        redisTemplate.opsForValue().set("user"+tokenNum, user);
        return tokenNum;
    }

    @Override
    public User getUserByCookie(String tokenNum) {
        User user = (User) redisTemplate.opsForValue().get("user"+tokenNum);
        if(user == null){
            return null;
        }
        return user;

    }



    @Override
    public int insert(String name,Integer age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        if(user==null){
            throw new RuntimeException("user对象为空");
        }
        int result = mapper.insert(name,age);
        if(result>0){
            String key = "USER_KEY" +user.getId();
            redisTemplate.setValueSerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(User.class));
            redisTemplate.opsForValue().set(key,user);
        }
        log.info("插入的数据"+ user);
        return result;
    }
}
