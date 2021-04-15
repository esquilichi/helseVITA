package spring.io;

import java.util.Map;

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

	@Autowired
	PatientService patientManager;

	@Autowired
	HealthPersonnelService healthPersonnelManager;

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

	@RequestMapping("/viewPatients")
	ModelAndView viewPatients() {
		var mv = new ModelAndView("mostrarPacientes");
		mv.addObject("users", patientManager.returnAll());
		return mv;
	}

	@RequestMapping("/viewHealthPersonnel")
	ModelAndView viewHealthPersonnel(){
		var mv = new ModelAndView("mostrarUsuarios");
		mv.addObject("users", healthPersonnelManager.returnAll());
		return mv;
	}

	@GetMapping("/search")
	ModelAndView search(@RequestParam Map<String,String> requestParams) {
		String input=requestParams.get("input");
   		String text=requestParams.get("username");
		
		
		if(input.equals("0")){
			User userTemp = manager.searchUsername(text);
			if(userTemp!=null){
				
				var mv = new ModelAndView("mostrar");
				mv.addObject("user", userTemp);
				return mv;
			}
				throw new UserNotFoundException(text);
		}else if(input.equals("1")){
			User userTemp = manager.searchEmail(text);
			if(userTemp!=null){
				var mv = new ModelAndView("mostrar");
				mv.addObject("user", userTemp);
				return mv;
			}
				throw new UserNotFoundException(text);
		}else if(input.equals("2")){
			User userTemp = manager.searchDni(text);
			if(userTemp!=null){
				var mv = new ModelAndView("mostrar");
				mv.addObject("user", userTemp);
				return mv;
			}
				throw new UserNotFoundException(text);
		}
		throw new IncorrectSearchParametersException();		

	}


	//Call to the exception
	@ExceptionHandler(UserNotFoundException.class)
	public ModelAndView exception(UserNotFoundException e){
		var mv = new ModelAndView("user-not-found");
		mv.addObject("username",e.getUsername());
		return mv;
	}

	@ExceptionHandler(IncorrectSearchParametersException.class)
	public ModelAndView exception2(IncorrectSearchParametersException e){
		var mv = new ModelAndView("incorrect-parameters");
		return mv;
	}
}