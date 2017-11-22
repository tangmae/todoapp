package todo.todoapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import todo.todoapp.dao.UserDAO;
import todo.todoapp.entity.User;

@SpringBootApplication
@ComponentScan("todo.todoapp")
public class App 
{
    public static void main( String[] args )
    {
    	ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
    	System.exit(SpringApplication.exit(context));
        	
    }
}
