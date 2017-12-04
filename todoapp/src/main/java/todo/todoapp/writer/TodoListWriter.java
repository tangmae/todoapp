package todo.todoapp.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import todo.todoapp.dao.TodoDAO;
import todo.todoapp.entity.Todo;

//public class TodoListWriter<Todo> implements ItemWriter<List<Todo>> {
	
public class TodoListWriter implements ItemWriter<Todo> {
	
	@Autowired
	private TodoDAO todoDAO;
	
	@Override 
	public void write(List<? extends Todo> todoList) throws Exception {
		for (Todo td: todoList) {
			todoDAO.insert(td);
		}
	}
	
	
	
//	@Override
//	public void write(List<? extends List<Todo>> todoList) throws Exception {
//		for (List<Todo> subList : todoList) {
//			for(Todo td : subList) {
//				int success = todoDAO.insert((todo.todoapp.entity.Todo) td);
//				System.out.println(" INSERT TODO " + success);
//			}
//		}
//	}


}
