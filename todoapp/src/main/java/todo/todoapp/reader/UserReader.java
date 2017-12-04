package todo.todoapp.reader;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemCountAware;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import todo.todoapp.dao.UserDAO;
import todo.todoapp.entity.User;

public class UserReader implements ItemReader<User> {
	
    private static final Logger LOG = LoggerFactory.getLogger(UserReader.class);
	
	@Autowired
	private UserDAO userdao;
	
	private List<User> userList;
	
	private int count = 0;
	
	@Override
	public User read() {
		
		LOG.info("-------- " + count);
		
		userList = userdao.findAll();
		
		if (count < userList.size()) {
			return userList.get(count++);
		}
		
		LOG.info("-------- " + count);

		return null;
	}
	

}
