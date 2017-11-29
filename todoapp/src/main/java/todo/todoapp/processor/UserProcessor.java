package todo.todoapp.processor;

import org.springframework.batch.item.ItemProcessor;

import todo.todoapp.entity.User;

public class UserProcessor implements ItemProcessor<User, User> {
	
	@Override
	public User process(User user) throws Exception {
		System.out.println("Processor is here");
		return user;
		
	}

}
