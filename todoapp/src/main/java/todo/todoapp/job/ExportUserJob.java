package todo.todoapp.job;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import todo.todoapp.entity.User;
import todo.todoapp.reader.UserReader;

@Configuration
@EnableBatchProcessing
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
    
    @Bean("exportAllUserJob")
    public Job exportAllUserJob() {
    	
    		return jobBuilderFactory.get("exportAllUserJob")
    			.start(retrieveUsersStep())
    			.build();
    }
    
    @Bean("retrieveUsersStep")
    public Step retrieveUsersStep() {
		return stepBuilderFactory.get("retrieveUsersStep")
				.chunk(1)
				.reader(reader())
				.build();
    }
    
    @Bean
    public UserReader reader() {
    		List<User> userList= userReader.read();
    		return userReader;
    }

}