package todo.todoapp.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import todo.todoapp.entity.Todo;

@Component
public class TodoDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
    private DataSource dataSource;
	
	private final String ALL_COLUMNS = "todo_id, project_id, user_id, title, description, assign_date, due_date, isdone ";
	private final String TABLE_NAME = " todos ";
	
	public List<Todo> findAll() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ");
		query.append(ALL_COLUMNS);
		query.append(" FROM ");
		query.append(TABLE_NAME);
		query.append(" ORDER BY todo_id asc");
		
		return this.jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper(TodoDAO.class));
	}
	
	public Todo findTodoById(int todoId) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ");
		query.append(ALL_COLUMNS);
		query.append(" FROM ");
		query.append(TABLE_NAME);
		query.append(" WHERE ");
		query.append(" todo_id = ? ");
		
		return this.jdbcTemplate.queryForObject(query.toString(), new Object[]{todoId}, Todo.class);
	}

}
