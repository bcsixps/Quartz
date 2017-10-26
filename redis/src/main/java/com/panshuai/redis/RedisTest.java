package com.panshuai.redis;

import java.beans.IntrospectionException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class RedisTest {

	public static boolean setObjectBySerialize(String key, Object object, RedisCacheTime redisCacheTime) {
		Jedis jedis = null;
		try {
			jedis = RedisUtil.getJedis();
			jedis.set(key.getBytes(), SerializeUtil.serialize(object));
			if (redisCacheTime != null && redisCacheTime != RedisCacheTime.PERMANENT_STORE)
				jedis.expire(key.getBytes(), redisCacheTime.getSeconds());
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}
	
	/**
	 * 使用反序列化的方式，根据key获取Object
	 * @param key
	 * @return
	 */
	public static Object getObjectByDeserialize(String key) {
		Jedis jedis = null;  
		try {
			jedis = RedisUtil.getJedis();
			Object o = SerializeUtil.deserialize(jedis.get(key.getBytes()));
			return o;
		} catch (Exception e) {
			return null;
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}
	
	/**
	 * 不使用管道初始化1W条数据
	 * @throws Exception
	 */
	public void NOTUsePipeline() throws Exception {
	    Jedis jedis = RedisUtil.getJedis();
	    long start_time = System.currentTimeMillis();
	    for (int i = 0; i < 10000; i++) {
	        jedis.set("aa_"+i, i+"");
	    }
	    System.out.println(System.currentTimeMillis()-start_time);
	}

	/**
	 * 使用管道初始化1W条数据
	 * @throws Exception
	 */
	public void usePipeline() throws Exception {
	    Jedis jedis = RedisUtil.getJedis();
	    long start_time = System.currentTimeMillis();
	    Pipeline pipelined = jedis.pipelined();
	    for (int i = 0; i < 10000; i++) {
	        pipelined.set("cc_"+i, i+"");
	    }
	    pipelined.sync();//执行管道中的命令
	    System.out.println(System.currentTimeMillis()-start_time);
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, IntrospectionException {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		for(int i=0;i<100000;i++){
			str.append("使用序列化使用序列化");
		}
		Doc doc = new Doc(str.toString(),str.toString(),str.toString(),str.toString(),str.toString(),str.toString(),str.toString(),str.toString(),str.toString(),str.toString(),str.toString(),str.toString());
//		Long start = System.currentTimeMillis();
//		setObjectBySerialize("testobj",doc,null);
//		Doc newdoc = (Doc)getObjectByDeserialize("testobj");
//		System.out.println(System.currentTimeMillis()-start);
//		System.out.println(newdoc.toString());
//		
//		
//		Long start = System.currentTimeMillis();
		Jedis jedis= RedisUtil.getJedis();
//		String jsonstr =  JSON.toJSONString(doc);
//		jedis.set("testobj1", jsonstr);
//		String newstr = jedis.get("testobj1");
//		JSONObject obj =  JSON.parseObject(newstr);
//		System.out.println(System.currentTimeMillis()-start);
//		
		Long start = System.currentTimeMillis();
		Map<String,String> map = MapAndBean.convertBean(doc);
		jedis.hmset("map",map);
		List<String> list = jedis.hmget("map", "publishTime","shortImage");
		System.out.println(list.get(0));
		System.out.println(System.currentTimeMillis()-start);
		
		
		
		
		
	}

}
