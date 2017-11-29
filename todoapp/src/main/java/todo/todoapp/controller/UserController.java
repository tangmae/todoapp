package todo.todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {
	
		@RequestMapping("/")
		public String index() {
			return "Welcome to Todo";
		}
	
		@RequestMapping("/allusers")
		public String showAllUsers(@RequestParam(value="messeges", required=false, defaultValue="Default") Model model) {
			model.addAttribute("messeges","Hi Users");
			return "Show Users";
		}

}
