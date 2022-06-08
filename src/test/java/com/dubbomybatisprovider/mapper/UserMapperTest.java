package com.dubbomybatisprovider.mapper;

import com.example.api.Pojo.User;
import com.example.dubbomybatisprovider.DubboMybatisProviderApplication;
import com.example.dubbomybatisprovider.mapper.UserMapper;
import com.example.dubbomybatisprovider.redis.RedisUtil;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DubboMybatisProviderApplication.class)
@SpringBootTest
@Slf4j
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisUtil redisUtil;
    @Test
    public void findTest(){

        log.info("*****************************************************************************************");
        User u1 = new User();
        u1.setId(1);
        User user1 = userMapper.queryById(u1.getId());
        log.info("用户1=[{}]",user1);


        log.info("*****************************************************************************************");
        User user3 = userMapper.queryById(3);
        log.info("用户3=[{}]",user3);

        log.info("*****************************************************************************************");
        User user4 = userMapper.queryById(4);
        log.info("用户4=[{}]",user4);

        log.info("*****************************************************************************************");
        User user5 = userMapper.queryById(5);
        log.info("用户5=[{}]",user5);
        log.info("*****************************************************************************************");

    }


}
