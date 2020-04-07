package com.ecb.ecb.job;

import com.ecb.ecb.service.EcbIntegrationService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.quartz.Job;

public class EcbSyncJob implements Job {

    @Autowired
    private EcbIntegrationService ecbIntegrationService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ecbIntegrationService.syncData();
    }
}
