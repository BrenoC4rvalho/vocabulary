package com.breno.vocabulary.quartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Configuration
public class JobScheduler {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SpringBeanJobFactory springBeanJobFactory;

    @Value("${job.execution.interval}")
    private  String jobExecutionInterval;

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(ExecuteJob.class).withIdentity("hourlyJob").storeDurably().build();
    }

    @Bean
    public Trigger trigger() {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withIdentity("hourlyTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(jobExecutionInterval))
                .build();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(Trigger trigger, JobDetail jobDetail) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(springBeanJobFactory);
        factory.setJobDetails(jobDetail);
        factory.setTriggers(trigger);
        return factory;
    }
}
