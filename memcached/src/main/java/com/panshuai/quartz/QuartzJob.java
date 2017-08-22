package com.panshuai.quartz;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

import net.spy.memcached.MemcachedClient;

public class QuartzJob implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {

		try {
			MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
			String str =  (String)mcc.get("test");
			System.out.println( new Date() + "--- to ---" + str);
			if(str == null){
				try {
					context.getScheduler().shutdown(true);
				} catch (SchedulerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
