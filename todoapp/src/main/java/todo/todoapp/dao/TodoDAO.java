package todo.todoapp.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import todo.todoapp.entity.Todo;

@Component
public class TodoDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
    private DataSource dataSource;
	
	private final String ALL_COLUMN = "todo_id, project_id, user_id, title, description, assign_date, due_date, isdone ";
	private final String ALL_COLUMN_WITHOUT_ID = " project_id, user_id, title, description, assign_date, due_date, isdone ";
	private final String TABLE_NAME = " todos ";
	
	public List<Todo> findAll() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ");
		query.append(ALL_COLUMN);
		query.append(" FROM ");
		query.append(TABLE_NAME);
		query.append(" ORDER BY todo_id asc");
		
		return this.jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<Todo>(Todo.class));
	}
	
	public Todo findTodoById(int todoId) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ");
		query.append(ALL_COLUMN);
		query.append(" FROM ");
		query.append(TABLE_NAME);
		query.append(" WHERE ");
		query.append(" todo_id = ? ");
		
		return this.jdbcTemplate.queryForObject(query.toString(), new Object[]{todoId}, Todo.class);
	}
	
	public int insert(Todo td) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ");
		query.append(TABLE_NAME);
		query.append(" ( ");
		query.append(ALL_COLUMN_WITHOUT_ID);
		query.append(" ) ");
		query.append(" VALUES ");
		query.append(" ( ");
		query.append(" :projectId ,");
		query.append(" :userId ,");
		query.append(" :title ,");
		query.append(" :description ,");
		query.append(" :assign_date ,");
		query.append(" :due_date ,");
		query.append(" :isdone ");
		query.append(" ); ");
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("projectId", td.getProjectId());
		params.addValue("userId", td.getUserId());
		params.addValue("title", td.getTitle());
		params.addValue("description", td.getDescription());
		params.addValue("assign_date", td.getAssignDate().toString());
		params.addValue("due_date", td.getDueDate().toString());
		params.addValue("isdone", td.isDone());
		
		return this.namedParameterJdbcTemplate.update(query.toString(), params);
	
	}

}
