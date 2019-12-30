package com.saqibayub.springbatch.batch1.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class Job1Configuration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean("job1Step1")
	public Step configureJob1Step1() {
		
		return stepBuilderFactory
			.get("job1Step1")
			.tasklet(new Job1Step1Tasklet1())
			.build()
			;
			
	}
	@Bean("job1")
	public Job configureJob1() {
		
		return jobBuilderFactory
			.get("job1")
			.start(configureJob1Step1())//FIXME
			.build()
			;
			
	}
	
}
