package org.kuaidi.web.springboot.util.redis;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description:获取获取连接
 * @author:pangshuaibing
 * @time:2018年9月3日
 */
@Service("redisFactory")
public class RedisFactory {
	
	/** 打印日志*/
	private static final Logger logger =  LoggerFactory.getLogger(RedisFactory.class);
	//jedispool连接	
	private static volatile JedisPool jedisPool;
	/** 获取redis配置信息*/
	private static final ResourceBundle REDISBUNDLE = java.util.ResourceBundle.getBundle("application");
	// 防止开发人员实例化
	private RedisFactory() {
		
	}
	/**
	 * 创建JedisPool对象连接池方法
	 * @return JedisPool
	 */
	public synchronized JedisPool getJedisPool(){ 
		//logger.info("创建JedisPool对象连接池...");
		if(jedisPool==null){
			try{
				String hostIp = REDISBUNDLE.getString("redis.host") ;//redis服务器IP
				int hostPort = Integer.valueOf(REDISBUNDLE.getString("redis.port"));//redis服务器端口号
				int timeout = Integer.valueOf(REDISBUNDLE.getString("redis.timeout"));//redis连接超时时间
				String hostPassword = REDISBUNDLE.getString("redis.pass");//redis连接密码
				//int hostBucket = Integer.valueOf(REDISBUNDLE.getString("redis.bucket"));//放入redis的哪个数据库
			    //创建JedisPool创建成功
				jedisPool = new JedisPool(new JedisPoolConfig(), hostIp, hostPort,timeout, hostPassword);
				//logger.info("成功连接Redis数据库，JedisPool创建成功.");
			}catch(Exception e){
				e.printStackTrace();
				logger.error("连接Redis出错：[{}]",e);
			}
		}
		//logger.info("创建JedisPool对象结束...");
		return jedisPool;
	}
	
	
	/**
	 * 获取Jedis对象
	 * @return Jedis对象
	 */
	public Jedis getResource() {
		//logger.info("开始获取Jedis对象...");
		Jedis jedis=null;
		try{
			jedis =getJedisPool().getResource();
		}catch(Exception e){
			e.printStackTrace();
			logger.error("获得Jedis出错：[{}]",e.getMessage());
		}
		//logger.info("获取Jedis对象结束...");
		return jedis;
	}

	/**
	 * 归还Jedis对象
	 * @param jedis 对象
	 */
	public void returnResource(Jedis jedis) {
		//logger.info("开始归还Jedis对象...");
		//如果jedis已经为空，则直接返回
		if (jedis == null) {
			return;
		}
		try {
			//归还jedis对象
			jedis.close();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("归还Jedis出错：[{}]",e.getMessage());
		}
		//logger.info("归还Jedis对象结束...");
	}
}
