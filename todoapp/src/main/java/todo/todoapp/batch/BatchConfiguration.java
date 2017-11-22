package todo.todoapp.batch;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.batch.api.chunk.ItemReader;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import todo.todoapp.entity.User;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
 
    @Autowired
    private JdbcTemplate jdbcTemplate;    
    @Bean("showUserStep")
    public Step showUsersStep() {
    	
    	return stepBuilderFactory.get("showUserStep")
    			.chunk(1)
    			.reader(reader())
    			.build();
    }
    			
   public FlatFileItemReader<User> reader(){
	   	FlatFileItemReader<User> userReader = new FlatFileItemReader<User>();
    	List<User> results = jdbcTemplate.query("SELECT * FROM users", new RowMapper<User>() {
			
			public User mapRow(ResultSet rs, int row) throws SQLException {
				return new User(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		});
		return userReader;	
    }
    
    @Bean
    public Job importUserJob() {
        return jobBuilderFactory.get("showUserJob")
                .start(showUsersStep())
                .build();
    }
    
}
