package todo.todoapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

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
