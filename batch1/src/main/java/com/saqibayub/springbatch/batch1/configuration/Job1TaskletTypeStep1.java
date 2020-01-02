package com.saqibayub.springbatch.batch1.configuration;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Job1TaskletTypeStep1 implements Tasklet {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	public static final String TASK_NAME="Job 1 Task 1";
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println("Hello Batch Step1 ");
		return RepeatStatus.FINISHED;
	}
	
	public Step getStep() {
		
		return stepBuilderFactory
			.get(TASK_NAME)
			.tasklet(this)
			.build()
			;
			
	}
}
