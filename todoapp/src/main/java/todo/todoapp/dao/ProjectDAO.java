package todo.todoapp.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import todo.todoapp.entity.Project;

@Component
public class ProjectDAO {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String TABLE_NAME = "projects";
	private final String ALL_COLUMN = " project_id, description, create_date ";
	
	public List<Project> findAll() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ");
		query.append(ALL_COLUMN);
		query.append(" FROM ");
		query.append(TABLE_NAME);
		query.append(" ORDER BY project_id ");
		
		return jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<Project>(Project.class));
		
	}
	
	
}
