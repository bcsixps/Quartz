package com.panshuai.memcached;

import java.net.InetSocketAddress;
import java.util.concurrent.Future;

import net.spy.memcached.MemcachedClient;

public class MemcachedJava {
   public static void main(String[] args) {
   
      try{
         // 连接本地的 Memcached 服务
         MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
         System.out.println("Connection to server sucessful.");
      
         // 存储数据
         Future fo = mcc.set("test", 30, "test old date");  //9秒后过期
      
         // 查看存储状态
         System.out.println("set status:" + fo.get());
         
         // 输出值
         System.out.println("runoob value in cache - " + mcc.get("test"));

         // 关闭连接
//         mcc.shutdown();
         
      }catch(Exception ex){
         System.out.println( ex.getMessage() );
      }
   }
}
