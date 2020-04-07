package com.ecb.ecb;

import com.ecb.ecb.job.EcbSyncJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@SpringBootApplication
public class EcbApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcbApplication.class, args);
    }


    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob().ofType(EcbSyncJob.class)
                .storeDurably()
                .withIdentity("Qrtz_Job_Detail")
                .withDescription("Invoke EcbSyncJob")
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail job) {
        return TriggerBuilder.newTrigger().forJob(job)
                .withIdentity("Qrtz_Trigger")
                .withDescription("Sample trigger")
                .withSchedule(simpleSchedule().repeatForever().withIntervalInHours(24))
                .build();
    }
}
