package com.panshuai.quartz.hello;



import org.quartz.CalendarIntervalScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

/**
 *CalendarIntervalTrigger
 *类似于SimpleTrigger，指定从某一个时间开始，以一定的时间间隔执行的任务。
 *但是不同的是SimpleTrigger指定的时间间隔为毫秒，没办法指定每隔一个月执行一次（每月的时间间隔不是固定值），
 *而CalendarIntervalTrigger支持的间隔单位有秒，分钟，小时，天，月，年，星期。
 *相较于SimpleTrigger有两个优势：
 *1、更方便，比如每隔1小时执行，你不用自己去计算1小时等于多少毫秒。 
 *2、支持不是固定长度的间隔，比如间隔为月和年。但劣势是精度只能到秒。
 *它适合的任务类似于：9:00 开始执行，并且以后每周 9:00 执行一次
 *它的属性有:
 *interval 执行间隔
 *intervalUnit 执行间隔的单位（秒，分钟，小时，天，月，年，星期）
 */

public class CalendarQuartz {
	public static void main(String[] args) throws SchedulerException {

		
		//1，获得一个调度器
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		
		//2，jobDetail & job   
		JobDetail job = JobBuilder.newJob(HelloQuartzJob.class).withIdentity("HelloQuartzJob").build();

//		//3,定义触发的条件

		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(new TriggerKey("HelloQuartzJob", "myTriggerGroup"))
				.withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInDays(1)) 
			    .build();

		//4，放入调度器
		scheduler.scheduleJob(job, trigger);
		//5，开启调度器
		scheduler.start();
	}

}
