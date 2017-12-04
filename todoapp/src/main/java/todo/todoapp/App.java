package todo.todoapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan("todo.todoapp")
public class App 
{
    public static void main( String[] args )
    {
//    	ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
//    	    	
//    	System.exit(SpringApplication.exit(context));
    	SpringApplication application = new SpringApplication(App.class);
        application.setWebEnvironment(false);
//        application.setBannerMode(Banner.Mode.OFF);
    	ConfigurableApplicationContext context = application.run(args);
    	System.exit(SpringApplication.exit(context));
    	
    }
}
