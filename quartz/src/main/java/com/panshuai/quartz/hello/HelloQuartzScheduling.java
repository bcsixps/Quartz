package com.panshuai.quartz.hello;


import java.util.Date;

import org.quartz.DailyTimeIntervalScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TimeOfDay;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.DailyTimeIntervalTriggerImpl;

/**
 * SimpleTrigger  指定从某一个时间开始，以一定的时间间隔（单位是毫秒）执行的任务。
 * @author admin
 *
 */
public class HelloQuartzScheduling {
	public static void main(String[] args) throws SchedulerException {

		
		//1，获得一个调度器
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		
		//2，jobDetail & job   
		JobDetail job = JobBuilder.newJob(HelloQuartzJob.class).withIdentity("HelloQuartzJob").build();

		//3,定义触发的条件
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(new TriggerKey("HelloQuartzJob", "myTriggerGroup")).startNow()
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(2))
				.build();
		
	
		//4，放入调度器
		scheduler.scheduleJob(job, trigger);
		//5，开启调度器
		scheduler.start();
	}

}
