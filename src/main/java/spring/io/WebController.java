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
			return searchUsername(text);
		}else if(input.equals("1")){
			return searchEmail(text);
		}else if(input.equals("3")){
			return searchDni(text);
		}
		throw new IncorrectSearchParametersException();		

	}

	
	ModelAndView searchUsername(String username) {
		User userTemp = manager.searchUsername(username);
		if(userTemp!=null){
			var mv = new ModelAndView("mostrar");
			mv.addObject("user", userTemp);
			return mv;
		}else{
			throw new UserNotFoundException(username);
		}
	}

	ModelAndView searchEmail(String email) {
		User userTemp = manager.searchEmail(email);
		if(userTemp!=null){
			var mv = new ModelAndView("mostrar");
			mv.addObject("user", userTemp);
			return mv;
		}else{
			throw new UserNotFoundException(email);
		}
	}


	ModelAndView searchDni(String dni) {
		User userTemp = manager.searchDni(dni);
		if(userTemp!=null){
			var mv = new ModelAndView("mostrar");
			mv.addObject("user", userTemp);
			return mv;
		}else{
			throw new UserNotFoundException(dni);
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