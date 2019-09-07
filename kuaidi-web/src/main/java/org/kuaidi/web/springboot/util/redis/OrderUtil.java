package org.kuaidi.web.springboot.util.redis;

import org.kuaidi.bean.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2019/8/5 10:36
 */
@Service
public class OrderUtil {

    @Autowired
    RedisUtil redisUtil;

    /** 打印日志*/
    private static final Logger logger =  LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    private RedisFactory redisFactory;

    public void incr(String key){
        //获取jedis对象
        Jedis jedis = redisFactory.getResource();
        try{
            //存储键值对
            jedis.incr(key);

        }finally{
            //归还jedis对象
            redisFactory.returnResource(jedis);
        }
    }

    public String getOrderNumber(String streetid){
        incr(streetid);
        String id = redisUtil.get(streetid);
        for(int i = id.length(); i < Config.ORDER_SIZE; i++){
            id = "0" + id;
        }
        return streetid+id;
    }
}
