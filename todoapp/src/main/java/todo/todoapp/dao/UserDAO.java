package todo.todoapp.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import todo.todoapp.entity.User;

@Component
public class UserDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
    private DataSource dataSource;
	
	private final String ALL_COLUMNS = "user_id, username, fullname ";
	private final String TABLE_NAME = " users ";
	
	public List<User> findAll() {
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT ");
		query.append(ALL_COLUMNS);
		query.append("FROM");
		query.append(TABLE_NAME);
		query.append("ORDER BY user_id");
		
		return this.jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<User>(User.class));
	}
	
	public User findById(int userId) {
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT ");
		query.append(ALL_COLUMNS);
		query.append("FROM");
		query.append(TABLE_NAME);
		query.append("WHERE user_id = ? ");
		
		return this.jdbcTemplate.queryForObject(query.toString(), new Object[]{userId}, User.class);
	}
	
	public int updateUsername(User user, String new_username) {
		
		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(TABLE_NAME);
		query.append(" SET ");
		query.append(" username = :username ");
		query.append(" WHERE ");
		query.append(" user_id = :user_id ");
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("user_id", user.getUserId());
		params.addValue("username", new_username);
		
		return this.jdbcTemplate.update(query.toString(), params);
	}
	
	public int updateFullName(User user, String new_username) {
		
		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(TABLE_NAME);
		query.append(" SET ");
		query.append(" username = :username ");
		query.append(" WHERE ");
		query.append(" user_id = :user_id ");
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("user_id", user.getUserId());
		params.addValue("username", new_username);
		
		return this.jdbcTemplate.update(query.toString(), params); //if success return 1
	}
	
}
