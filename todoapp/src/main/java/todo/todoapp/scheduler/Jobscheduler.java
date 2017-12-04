package todo.todoapp.scheduler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import todo.todoapp.job.ExportUserJob;
import todo.todoapp.job.GenerateTodoJob;

@Component
public class Jobscheduler {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private ExportUserJob exportUserJob;
	
	@Autowired
	private GenerateTodoJob generateTodoJob;
	
	@Scheduled(initialDelay = 1000, fixedRate = 1000)
	public void runJobSchedule() {
		
		Map<String, JobParameter> confMap = new HashMap<>();
		confMap.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters jobParameters = new JobParameters(confMap);
		
		 try {
	            jobLauncher.run(generateTodoJob.generateTodoJob(), jobParameters);

	        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException
	                | JobParametersInvalidException | org.springframework.batch.core.repository.JobRestartException e) {
	            System.out.println(e.getMessage());
	        }
	}
	
}
