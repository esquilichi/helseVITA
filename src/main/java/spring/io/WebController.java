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


	@RequestMapping("/viewPatients")
	ModelAndView viewPatients() {
		var mv = new ModelAndView("mostrarPacientes");
		mv.addObject("users", patientManager.returnAll());
		return mv;
	}

	@RequestMapping("/viewHealthPersonnel")
	ModelAndView viewHealthPersonnel(){
		var mv = new ModelAndView("mostrarSanitario");
		mv.addObject("users", healthPersonnelManager.returnAll());
		return mv;
	}

	@GetMapping("/search")
	ModelAndView search(@RequestParam Map<String,String> requestParams) {
		String input=requestParams.get("input");
   		String text=requestParams.get("username");
		User userTemp = new User();

		if(input.equals("0")){
			userTemp = manager.searchUsername(text);
		}else if(input.equals("1")){
			userTemp = manager.searchEmail(text);
		}else if(input.equals("2")){
			userTemp = manager.searchDni(text);
		}else{
			throw new IncorrectSearchParametersException();	
		}
		if(userTemp!=null){
			var mv = new ModelAndView("mostrar");
			mv.addObject("user", userTemp);
			return mv;
		}
		throw new UserNotFoundException(text);	
	}

	@GetMapping("/searchPatient")
	ModelAndView searchPatient(@RequestParam Map<String,String> requestParams) {
		String input=requestParams.get("input");
   		String text=requestParams.get("username");
		Patient userTemp = new Patient();

		if(input.equals("0")){
			userTemp = patientManager.searchUsername(text);
		}else if(input.equals("1")){
			userTemp = patientManager.searchEmail(text);
		}else if(input.equals("2")){
			userTemp = patientManager.searchDni(text);
		}else{
			throw new IncorrectSearchParametersException();	
		}
		if(userTemp!=null){
			var mv = new ModelAndView("mostrar");
			mv.addObject("user", userTemp);
			return mv;
		}
		throw new UserNotFoundException(text);	
	}

	@GetMapping("/searchHealthPersonnel")
	ModelAndView searchHealthPersonnel(@RequestParam Map<String,String> requestParams) {
		String input=requestParams.get("input");
   		String text=requestParams.get("username");
		HealthPersonnel userTemp = new HealthPersonnel();

		if(input.equals("0")){
			userTemp = healthPersonnelManager.searchUsername(text);
		}else if(input.equals("1")){
			userTemp = healthPersonnelManager.searchEmail(text);
		}else if(input.equals("2")){
			userTemp = healthPersonnelManager.searchDni(text);
		}else{
			throw new IncorrectSearchParametersException();	
		}
		if(userTemp!=null){
			var mv = new ModelAndView("mostrar");
			mv.addObject("user", userTemp);
			return mv;
		}
		throw new UserNotFoundException(text);	
	}

	@RequestMapping("/chooseDoctors")
	public ModelAndView chooseDoc(){
		
		var mv = new ModelAndView("choose-doctor");
		mv.addObject("doctors",healthPersonnelManager.returnAll());
		
		return mv;
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
	@GetMapping("/chooseDoctors")
	public ModelAndView chooseDoc(@RequestParam(required = false)  String id){
		if (id != null){
			var mv = new ModelAndView("choose-doctor");
			mv.addObject("doctors",healthPersonnelManager.returnAll());
			mv.addObject("id", Long.parseLong(id) - 1);
			return mv;
		}else{
			var mv = new ModelAndView("index");
			return mv;
		}
	}
}