package com.saqibayub.springbatch.batch1.configuration;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class Job1Configuration {

	@Autowired
	private Job1TaskletTypeStep1 task1;
	
	public static final String JOB_NAME="Job 1 Task 1";
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Bean("job5")
	public Job job5TransitionJob() {
		
		return jobBuilderFactory
			.get("job5TransitionJob")
			.start(step1())
			.on("COMPLETED").to(step2())
			.from(step2()).on("COMPLETED").to(step3())
			.from(step3()).end()
			.build()
			;
	}


	@Bean("job4")
	public Job job4TransitionJob() {
		
		return getSimpleJobBuilder("job4TransitionJob").build();
	}
	@Bean("job3")
	public Job job3TransitionJob() {
		
		return getSimpleJobBuilder("job3TransitionJob").build();
	}
	@Bean("job2")
	public Job job2TransitionJob() {
		
		return jobBuilderFactory
			.get("job2TransitionJob")
			.start(step1())
			.next(step2())
			.next(step3())
			.build()
			;
	}
	@Bean("job1")
	public Job configureJob1() {
		
		return jobBuilderFactory
			.get(JOB_NAME)
			.start(task1.getStep())
			.build()
			;
	}
	
	private SimpleJobBuilder getSimpleJobBuilder(String jobName) {
		
		return jobBuilderFactory
			.get(jobName)
			.start(step1())
			.next(step2())
			.next(step3());
	}

	private Step step1() {
		return stepBuilderFactory
		.get("Step#1")
		.tasklet((contribution, chunkContext) ->{
			System.out.println("Job Step1 ");
			return RepeatStatus.FINISHED;
		})
		.build()
		;
	}
	
	private Step step2() {
		return stepBuilderFactory
		.get("Step#2")
		.tasklet((contribution, chunkContext) ->{
			System.out.println("Job Step2 ");
			return RepeatStatus.FINISHED;
		})
		.build()
		;
	}
	
	private Step step3() {
		return stepBuilderFactory
		.get("Step#3")
		.tasklet((contribution, chunkContext) ->{
			System.out.println("Job Step 3 ");
			return RepeatStatus.FINISHED;
		})
		.build()
		;
	}	
}