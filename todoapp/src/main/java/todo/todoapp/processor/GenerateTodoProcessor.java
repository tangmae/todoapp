package todo.todoapp.processor;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import todo.todoapp.dao.ProjectDAO;
import todo.todoapp.entity.Project;
import todo.todoapp.entity.Todo;
import todo.todoapp.entity.User;

public class GenerateTodoProcessor implements ItemProcessor<User, Todo> {
	
	@Autowired
	private ProjectDAO projectDao;
	
	public Todo process(User user) throws Exception {
		
		DateTime today = DateTime.now();

		Todo todo = new Todo();
		todo.setUserId(user.getUserId());
		todo.setProjectId(1);
		todo.setTitle("TODO for Test");
		todo.setDescription("Generated " + Math.ceil((Math.random()*(100))));
		todo.setAssignDate(today.toDate());
		todo.setDueDate(today.plusDays(1).toDate());

		return todo;
	}
	

	public List<Todo> pprocess(User user) throws Exception {
		List<Todo> todoList = new ArrayList();
		
		List<Project> projectList = projectDao.findAll();
		
		DateTime today = DateTime.now();
		
		for(Project pj : projectList) {
			Todo todo = new Todo();
			todo.setUserId(user.getUserId());
			todo.setProjectId(pj.getProjectId());
			todo.setTitle("TODO for " + pj.getDescription());
			todo.setDescription("Generated " + Math.ceil((Math.random()*(100))));
			todo.setAssignDate(today.toDate());
			todo.setDueDate(today.plusDays(1).toDate());
			
			todoList.add(todo);
		}
		
		return todoList;
	}
	

}
