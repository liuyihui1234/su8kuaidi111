package org.kuaidi.web.springboot.util.redis;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

/**
 * @Description: Redis操作工具类
 * @author:pangshuaibing
 * @time:2018年9月3日
 */
@Service
public class RedisUtil {

	/** 打印日志*/
	private static final Logger logger =  LoggerFactory.getLogger(RedisUtil.class);
	
	@Autowired
	private RedisFactory redisFactory;


	/**
	 * 给key重新设置过期时间
	 * @param key
	 * @param expire
	 */

	public void setExpire(String key, int expire){
		//获取jedis对象
		Jedis jedis = redisFactory.getResource();
		if(expire>0) {
			jedis.expire(key, expire);
		}
		redisFactory.returnResource(jedis);
	}

	/**
	 * 存储键值对方法
	 * @param key 键值对的键
	 * @param value 键值对的值
	 * @param expire 键值对的过期时间
	 */
	public void set(String key,String value,int expire){
		//获取jedis对象
		Jedis jedis = redisFactory.getResource();
		try{
			//存储键值对
			jedis.set(key,value);
			//如果过期时间不为0，则设置键值对的过期时间
			if(expire != 0){
				jedis.expire(key, expire);
		 	}
		}finally{
			//归还jedis对象
			redisFactory.returnResource(jedis);
		}
	}
	
	/**
	 * 存储键值对方法
	 * @param key 键值对的键
	 * @param value 键值对的值 object
	 * @param expire 键值对的过期时间
	 */
	public void set(String key, Object value, int expire){
		//获取jedis对象
		Jedis jedis = redisFactory.getResource();
		try{
			//存储键值对
			jedis.set(key, value.toString());
			//如果过期时间不为0，则设置键值对的过期时间
			if(expire != 0){
				jedis.expire(key, expire);
		 	}
		}finally{
			//归还jedis对象
			redisFactory.returnResource(jedis);
		}
	}
	
	
	/**
	 * 获取键值对的值方法
	 * @param key 键值对的键
	 * @return 键值对的值
	 */
	public String get(String key){
		//获取jedis对象
		Jedis jedis = redisFactory.getResource();
		//获取的默认值为空
		String value = null;
		try{
			//如果存在
			if(jedis.exists(key)){
				value = jedis.get(key);
			}else {
				logger.info("[{}]不存在",key);
			}
		}finally{
			//归还jedis对象
			redisFactory.returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * 判断键值对是否存在，而不是真的获取键值对
	 * @param key 需要判断的键
	 * @return 是否存在标识
	 */
	public boolean isExist(String key){
		//获取jedis对象
		Jedis jedis = redisFactory.getResource();
		//判断的键值对默认不存在
		boolean exist = false;
		try{
			//如果存在
			if(jedis.exists(key)){
				exist = true;
			}else {
				logger.info("[{}]不存在",key);
			}
		}finally{
			//归还jedis对象
			redisFactory.returnResource(jedis);
		}
		return exist;
	}
	
	
	/**
	 * 根据键值对的键，删除键值对
	 * @param key 删除的键值对的键
	 */
	public void del(String key) {
		//获取jedis对象
		Jedis jedis=redisFactory.getResource();
		try{
			//如果存在则删除
			if(jedis.exists(key)){
				jedis.del(key);
			}else {
				logger.info("[{}]不存在",key);
			}
		}finally{
			//归还jedis对象
			redisFactory.returnResource(jedis);
		}
	}
	
	/*
	 * 获取redis中所有的key列表
	 */
	public Set<String> getAllKeys() {
		//获取jedis对象
		Jedis jedis=redisFactory.getResource();
		Set<String> keys = null;//存储所有的keys
		try {
			keys = jedis.keys("*");
			if(keys != null && keys.size() != 0) {
				//logger.info("共获取[{}]个key",keys.size());
			}else {
				logger.info("共获取[0]个key");
			}
		} finally {
			//归还jedis对象
			redisFactory.returnResource(jedis);
		}
		return keys;
	}
	/*
	 * 获取redis中所有的key列表
	 */
	public Set<String> getAllKeys(String expression) {
		//获取jedis对象
		Jedis jedis=redisFactory.getResource();
		Set<String> keys = null;//存储所有的keys
		try {
			keys = jedis.keys(expression);
			if(keys != null && keys.size() != 0) {
				//logger.info("共获取[{}]个key",keys.size());
			}else {
				logger.info("共获取[0]个key");
			}
		} finally {
			//归还jedis对象
			redisFactory.returnResource(jedis);
		}
		return keys;
	}
	/**
	 * 根据前缀通配符获取所有的key列表
	 * @param preKey key的前缀通配符
	 * @return 匹配的所有的key列表
	 */
	public Set<String> getAllLikeKeys(String preKey) {
		//获取jedis对象
		Jedis jedis=redisFactory.getResource();
		Set<String> keys = null;//存储所有匹配的keys
		try {
			keys = jedis.keys(preKey + "*");
			if(keys != null && keys.size() != 0) {
				//logger.info("共获取[{}]个key",keys.size());
			}else {
				logger.info("共获取[0]个key");
			}
		} finally {
			//归还jedis对象
			redisFactory.returnResource(jedis);
		}
		return keys;
	}
	
	/**
	 * 删除所有键值对
	 */
	public void delAllKeys() {
		//获取jedis对象
		Jedis jedis=redisFactory.getResource();
		try {
			//获取所有的keys
			Set<String> keys = jedis.keys("*");
			int i = 0;//记录删除的key数量
			if(keys != null && keys.size() != 0) {
				for (String key : keys) {
					i++;
					jedis.del(key);
				}
			}
			//logger.info("删除所有key完成，数量为：[{}]",i);
		} finally {
			//归还jedis对象
			redisFactory.returnResource(jedis);
		}
	}
	
	/**
	 * 删除前缀通配符的key列表
	 * @param preKey key的前缀通配符
	 */
	public void delAllLikeKeys(String preKey) {
		//获取jedis对象
		Jedis jedis=redisFactory.getResource();
		try {
			//获取所有的keys
			Set<String> keys = jedis.keys(preKey + "*");
			int i = 0;//记录删除的key数量
			if(keys != null && keys.size() != 0) {
				for (String key : keys) {
					i++;
					jedis.del(key);
				}
			}
			//logger.info("删除前缀通配的所有key完成，数量为：[{}]",i);
		} finally {
			//归还jedis对象
			redisFactory.returnResource(jedis);
		}
	}
	
	/**
	 * 存储键值对方法-字节
	 * @param key 键值对的键
	 * @param value 键值对的值
	 * @param expire 键值对的过期时间
	 */
	public void set(byte[] key,byte[] value,int expire){
		//获取jedis对象
		Jedis jedis = redisFactory.getResource();
		try{
			//存储键值对
			jedis.set(key,value);
			//如果过期时间不为0，则设置键值对的过期时间
			if(expire != 0){
				jedis.expire(key, expire);
		 	}
		}finally{
			//归还jedis对象
			redisFactory.returnResource(jedis);
		}
	}
	
	/**
	 * 获取键值对的值方法-字节
	 * @param key 键值对的键
	 * @return 键值对的值
	 */
	public byte[] get(byte[] key){
		//获取jedis对象
		Jedis jedis = redisFactory.getResource();
		//获取的默认值为空
		byte[] value = null;
		try{
			//如果存在
			if(jedis.exists(key)){
				value = jedis.get(key);
			}else {
				logger.info("[{}]不存在",key);
			}
		}finally{
			//归还jedis对象
			redisFactory.returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * 根据键值对的键，删除键值对-字节
	 * @param key 删除的键值对的键
	 */
	public void del(byte[] key) {
		//获取jedis对象
		Jedis jedis=redisFactory.getResource();
		try{
			//如果存在则删除
			if(jedis.exists(key)){
				jedis.del(key);
			}else {
				logger.info("[{}]不存在",key);
			}
		}finally{
			//归还jedis对象
			redisFactory.returnResource(jedis);
		}
	}
}
