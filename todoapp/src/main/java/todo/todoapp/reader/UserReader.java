package todo.todoapp.reader;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import todo.todoapp.dao.UserDAO;
import todo.todoapp.entity.User;

@Component
public class UserReader implements ItemReader<List<User>>{
	
	@Autowired
	private UserDAO userdao;

	@Override
	public List<User> read() {
		List<User> userList = userdao.findAll();
		for (User u : userList) {
			System.out.println(u.toString());
		}
		return userList;
	}
	

}
