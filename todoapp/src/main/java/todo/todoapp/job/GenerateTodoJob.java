package todo.todoapp.job;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import todo.todoapp.entity.Todo;
import todo.todoapp.entity.User;
import todo.todoapp.listener.JobCompletionListener;
import todo.todoapp.processor.GenerateTodoProcessor;
import todo.todoapp.reader.UserReader;
import todo.todoapp.tasklet.BaseTasklet;
import todo.todoapp.writer.TodoListWriter;

@Configuration
@EnableBatchProcessing
public class GenerateTodoJob {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired 
	private JobCompletionListener jobCompleteListener;
	
	@Bean("GenerateTodoJob")
	public Job generateTodoJob() {
		return jobBuilderFactory.get("GenerateTodoJob")
				.incrementer(new RunIdIncrementer())
				.listener(jobCompleteListener)
				.start(generateTodoStep())
				.next(step2())
				.build();
	}
	
	@Bean("GenerateTodoStep")
	public Step generateTodoStep() {
		return stepBuilderFactory.get("GenerateTodoStep")
				.<User, Todo>chunk(1)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}
	
	@Bean("UserReader")
	@StepScope
	public UserReader reader() {
		UserReader userReader = new UserReader();
		return userReader;
	}
	
	@Bean("GenerateNewTodoProcessor")
	@StepScope
	public GenerateTodoProcessor processor() {
		GenerateTodoProcessor generateTodoProcessor = new GenerateTodoProcessor();
		return generateTodoProcessor;
	}
	
	@Bean("GenerateTodoWriter")
	@StepScope
	public TodoListWriter writer() {
		TodoListWriter writer = new TodoListWriter();
		return writer;
	}
	
	@Bean("Step2")
	public Step step2() {
		System.out.println("Here is STEP 2 !!! ");
		return stepBuilderFactory.get("step2")
				.tasklet(new BaseTasklet())
				.build();
	}

}
