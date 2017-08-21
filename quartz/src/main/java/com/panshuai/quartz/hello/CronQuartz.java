package com.panshuai.quartz.hello;



import java.io.IOException;

import org.quartz.CalendarIntervalScheduleBuilder;
import org.quartz.CronScheduleBuilder;
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
 *适合于更复杂的任务，它支持类型于Linux Cron的语法（并且更强大）。基本上它覆盖了以上三个Trigger的绝大部分能力（但不是全部）—— 当然，也更难理解。
它适合的任务类似于：每天0:00,9:00,18:00各执行一次。
它的属性只有:
Cron表达式。但这个表示式本身就够复杂了。下面会有说明。
 */

public class CronQuartz {
	public static void main(String[] args) throws SchedulerException, IOException {

		
		//1，获得一个调度器
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		
		//2，jobDetail & job   
		JobDetail job = JobBuilder.newJob(HelloQuartzJob.class).withIdentity("HelloQuartzJob").build();

		//3,定义触发的条件

		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(new TriggerKey("HelloQuartzJob", "myTriggerGroup"))
				.withSchedule(CronScheduleBuilder.cronSchedule("0 59 15 * * ?"))   // 每天15：54执行一次
			    .build();
		
		//4，放入调度器
		scheduler.scheduleJob(job, trigger);
		//5，开启调度器
		scheduler.start();
		System.in.read();
		scheduler.shutdown();
	}

}
