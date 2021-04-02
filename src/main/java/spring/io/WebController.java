package spring.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {
	@Autowired
	UserService gestor;

	@RequestMapping("/")
	ModelAndView index(Model model) {
		var mv = new ModelAndView("index");
		mv.addObject("Usuarios", gestor.devolverTodos());
		return mv;
	}

	@RequestMapping("/mostrar/{id}")
	ModelAndView mostrar(Model model, @PathVariable long id) {
		Usuario userTemp = gestor.devolverUsuario(id - 1);
		var mv = new ModelAndView("mostrar");
		mv.addObject("usuario", userTemp);
		return mv;
	}


	@GetMapping("/buscar")
	ModelAndView buscar(Model model, @RequestParam String username) {
		Usuario userTemp = gestor.buscar(username);
		if(userTemp!=null){
			var mv = new ModelAndView("mostrar");
			mv.addObject("usuario", userTemp);
			return mv;
		}else{
			throw new UserNotFoundException(username);
		}
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ModelAndView excepcion(UserNotFoundException e){
		var mv = new ModelAndView("user-not-found");
		mv.addObject("username",e.getUsername());
		return mv;
	}
	
}