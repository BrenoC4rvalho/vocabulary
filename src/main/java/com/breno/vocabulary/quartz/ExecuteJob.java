package com.breno.vocabulary.quartz;

import com.breno.vocabulary.Service.OpenAiChatService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ExecuteJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(ExecuteJob.class);

    private final OpenAiChatService openAiChatService;

    @Autowired
    public ExecuteJob(OpenAiChatService openAiChatService){
        this.openAiChatService = openAiChatService;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {

            logger.info("Starting job execution");

            System.out.println(openAiChatService.getVocabulary());

            logger.info("Job executed successfully");

        } catch (Exception e) {
            logger.error("Error during job execution", e);
        }
    }

}
