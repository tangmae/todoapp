package todo.todoapp.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import todo.todoapp.dao.TodoDAO;

public class TodoListWriter<Todo> implements ItemWriter<List<Todo>> {
	
	@Autowired
	private TodoDAO todoDAO;
	
	@Override
	public void write(List<? extends List<Todo>> todoList) throws Exception {
		for (List<Todo> subList : todoList) {
			for(Todo td : subList) {
				int success = todoDAO.insert((todo.todoapp.entity.Todo) td);
			}
		}
	}


}
