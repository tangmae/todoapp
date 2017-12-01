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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import todo.todoapp.entity.User;
import todo.todoapp.processor.UserProcessor;
import todo.todoapp.reader.UserReader;

public class ExportUserJob {
	
	@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory; 
    
    @Autowired
    private DataSource dataSource;
    
    @Autowired 
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private UserReader userReader;
    
    @Bean("DisplayAllUserJob")
    public Job printAllUserJob() {
    	return jobBuilderFactory.get("DisplayAllUserJob")
    			.incrementer(new RunIdIncrementer())
    			.start(retrieveUsersStep())
    			.build();
    }
    
    @Bean("retrieveUsersStep")
    public Step retrieveUsersStep() {
		return stepBuilderFactory.get("retrieveUsersStep")
				.<User, User>chunk(1)
				.reader(reader())
				.processor(processor())
				.build();
    }
    
    @Bean("allUserReader")
    @StepScope
    public UserReader reader() {
    	UserReader userReader = new UserReader();
    	return userReader;
    }
    
    @Bean("allUserProcessor")
    @StepScope
    public UserProcessor processor() {
    	UserProcessor processor = new UserProcessor();
    	return processor;
    	
    }
}