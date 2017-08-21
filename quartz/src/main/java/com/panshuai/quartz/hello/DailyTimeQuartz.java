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
 *  DailyTimeIntervalTrigger
 *	指定每天的某个时间段内，以一定的时间间隔执行任务。并且它可以支持指定星期。
 *	它适合的任务类似于：指定每天9:00 至 18:00 ，每隔70秒执行一次，并且只要周一至周五执行。
 *	它的属性有:
 *	startTimeOfDay 每天开始时间
 *	endTimeOfDay 每天结束时间
 *	daysOfWeek 需要执行的星期
 *	interval 执行间隔
 *	intervalUnit 执行间隔的单位（秒，分钟，小时，天，月，年，星期）
 *  repeatCount 重复次数
 * @author admin
 *
 */
public class DailyTimeQuartz {
	public static void main(String[] args) throws SchedulerException {

		
		//1，获得一个调度器
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		
		//2，jobDetail & job   
		JobDetail job = JobBuilder.newJob(HelloQuartzJob.class).withIdentity("HelloQuartzJob").build();

//		//3,定义触发的条件

		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(new TriggerKey("HelloQuartzJob", "myTriggerGroup"))
				.withSchedule(DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
			    .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(15, 27)) //第天9：00开始
			    .endingDailyAt(TimeOfDay.hourAndMinuteOfDay(15, 28)) //16：00 结束 
			    .onDaysOfTheWeek(1,2,3,4,5) //周一至周五执行
			    .withIntervalInSeconds(2) //每间隔1小时执行一次
			    .withRepeatCount(100)) //最多重复100次（实际执行100+1次）
			    .build();

		//4，放入调度器
		scheduler.scheduleJob(job, trigger);
		//5，开启调度器
		scheduler.start();
	}

}
