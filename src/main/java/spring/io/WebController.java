package spring.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


//The WebController is in charge of showing data through MVC (Modelo Vista Controlador)
@Controller
public class WebController {

	//Pointer to the userService class. We do not need to initialize the userService class.
	@Autowired
	UserService manager;

	@RequestMapping("/")
	ModelAndView index() {
		var mv = new ModelAndView("index");
		mv.addObject("Users", manager.returnAll());
		return mv;
	}

	@RequestMapping("/mostrar/{id}")
	ModelAndView view(@PathVariable long id) {
		User userTemp = manager.returnUser(id - 1);
		var mv = new ModelAndView("mostrar");
		mv.addObject("user", userTemp);
		return mv;
	}

	@RequestMapping("/viewUsers")
	ModelAndView viewUsers() {
		var mv = new ModelAndView("mostrarUsuarios");
		mv.addObject("users", manager.returnAll());
		return mv;
	}


	@GetMapping("/buscar")
	ModelAndView search(@RequestParam String username) {
		User userTemp = manager.search(username);
		if(userTemp!=null){
			var mv = new ModelAndView("mostrar");
			mv.addObject("user", userTemp);
			return mv;
		}else{
			throw new UserNotFoundException(username);
		}
	}

	//Call to the exception
	@ExceptionHandler(UserNotFoundException.class)
	public ModelAndView exception(UserNotFoundException e){
		var mv = new ModelAndView("user-not-found");
		mv.addObject("username",e.getUsername());
		return mv;
	}
	
}