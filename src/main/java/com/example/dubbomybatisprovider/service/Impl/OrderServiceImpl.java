package com.example.dubbomybatisprovider.service.Impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.api.IGoodsService;
import com.example.api.IOrderService;
import com.example.api.Pojo.Goods;
import com.example.api.Pojo.Order;
import com.example.api.Pojo.User;
import com.example.api.util.MD5Util;
import com.example.api.util.UUIDutil;
import com.example.dubbomybatisprovider.mapper.GoodsMapper;
import com.example.dubbomybatisprovider.mapper.OrderMapper;
import com.example.dubbomybatisprovider.redis.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
@DubboService(interfaceClass = IOrderService.class, version = "1.0.0",timeout=1000)
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Resource
    private IGoodsService iGoodsService;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public boolean checkCaptcha(User user,Long goodsId,String captcha) {
        if(StringUtils.isEmpty(captcha)){
            return false;
        }
        String redisCaptcha = (String) redisTemplate.opsForValue().get("captcha:"+user.getId()+":"+goodsId);
        return captcha.equals(redisCaptcha);
    }

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private GoodsMapper goodsMapper;
    @Override
    public Order create(Long userId, Goods goods) {
        Order order = new Order();
        order.setUserId(userId);
        order.setGoodsId(goods.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goods.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(goods.getGoodsPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreatDate(new Date());
        goodsMapper.updateNumById(goods.getId());
        orderMapper.insertOrder(order);
        return order;
    }
    private static final String MIAOSHA_SALT = "123456";

    /**
     * 根据用户cookie用md5生成路径
     * @param user
     * @param goodsId
     * @return
     */
    @Override
    public String createPath(User user, Long goodsId) {
        String str = MD5Util.MD5(UUIDutil.getUUID() + MIAOSHA_SALT);
        redisTemplate.opsForValue().set("path:"+user.getId()+":"+goodsId,str,60,TimeUnit.SECONDS);
        return str;
    }

    /**
     * 验证路径是否正确
     * @param user
     * @param goodsId
     * @param path
     * @return
     */
    @Override
    public boolean checkPath(User user, Long goodsId, String path) {
        if(user==null||goodsId<0||StringUtils.isEmpty(path)){
            return false;
        }
        String redisPath = (String) redisTemplate.opsForValue().get("path:"+user.getId()+":"+goodsId);
        return path.equals(redisPath);
    }

    @Override
    public long getResult(Integer id, long goodsId) {
        return 0;
    }

}
