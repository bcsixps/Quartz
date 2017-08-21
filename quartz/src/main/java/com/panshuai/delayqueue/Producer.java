package com.panshuai.delayqueue;

import java.util.Arrays;
import java.util.concurrent.DelayQueue;

/**
 * 
 * java 延时队列 可以用于定时发送，-- 可以自己决定排序条件
 * 
 */

public class Producer {

	public static void main(String[] args) throws InterruptedException {

		// 创建延时队列

		DelayQueue<Message> queue = new DelayQueue<Message>();

		Message m1 = new Message(1, "3S", 3000);

		Message m2 = new Message(2, "50S", 50000);

		Message m3 = new Message(3, "30S", 30000);

		Message m4 = new Message(4, "1S", 1000);
		System.out.println(m1);
		System.out.println(m2);
		System.out.println(m3);
		System.out.println(m4);
		
		

		queue.offer(m1);   //会调用我们的compareTo方法来维护为一个有序队列

		queue.offer(m2);

		queue.offer(m3);

		queue.offer(m4);
		
		
		System.out.println(Arrays.toString(queue.toArray()));

		// 启动消费线程

		new Thread(new Consumer(queue)).start();

//		Thread.sleep(100);
//
//		Message m5 = new Message(7, "7S", 7000);
//
//		Message m6 = new Message(5, "14S", 14000);
//
//		Message m7 = new Message(9, "5S", 5000);
//
//		queue.offer(m5);
//
//		queue.offer(m6);
//
//		queue.offer(m7);

	}

}