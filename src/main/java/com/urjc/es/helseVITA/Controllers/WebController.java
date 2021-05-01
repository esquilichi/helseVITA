package com.urjc.es.helseVITA.Controllers;

import com.urjc.es.helseVITA.Entities.HealthPersonnel;
//import com.urjc.es.helseVITA.Entities.HealthPersonnel;
import com.urjc.es.helseVITA.Entities.Patient;
import com.urjc.es.helseVITA.Exceptions.IncorrectSearchParametersException;
import com.urjc.es.helseVITA.Exceptions.UserNotFoundException;
import com.urjc.es.helseVITA.Services.HealthPersonnelService;
import com.urjc.es.helseVITA.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Map;

@Controller
public class WebController {

    @Autowired
    PatientService patientService;

    @Autowired
    HealthPersonnelService healthPersonnelService;

    @RequestMapping("/")
    ModelAndView index() {
        var mv = new ModelAndView("index");
        mv.addObject("Users", patientService.returnAllPatients());
        return mv;
    }

    @RequestMapping("/mostrar/{id}")
    ModelAndView view(@PathVariable Integer id) {
        Patient temp = patientService.returnPatient(id);
        var mv = new ModelAndView("mostrar");
        mv.addObject("user", temp);
        return mv;
    }


    @RequestMapping("/viewPatients")
    ModelAndView viewPatients() {
        var mv = new ModelAndView("mostrarPacientes");
        mv.addObject("users", patientService.returnAllPatients());
        return mv;
    }

    @RequestMapping("/viewHealthPersonnel")
    ModelAndView viewHealthPersonnel(){
        var mv = new ModelAndView("mostrarSanitario");
        mv.addObject("users", healthPersonnelService.returnAllHealthPersonnels());
        return mv;
    }

    //This is for users, don´t delete in case we go back to
    //using Inheritance
    /*@GetMapping("/search")
    ModelAndView search(@RequestParam Map<String,String> requestParams) {
        String input=requestParams.get("input");
        String text=requestParams.get("username");
        Patient temp = new Patient();

        if(input.equals("0")){
            temp = patientService.searchUsername(text);
        }else if(input.equals("1")){
            temp = patientService.searchEmail(text);
        }else if(input.equals("2")){
            temp = patientService.searchDNI(text);
        }else{
            throw new IncorrectSearchParametersException();
        }
        if(temp != null){
            var mv = new ModelAndView("mostrar");
            mv.addObject("user", temp);
            return mv;
        }
        throw new UserNotFoundException(text);
    }
    */
    @GetMapping("/searchPatient")
    ModelAndView searchPatient(@RequestParam Map<String,String> requestParams) {
        String input=requestParams.get("input");
        String text=requestParams.get("username");
        Patient userTemp = new Patient();

        if(input.equals("0")){
            userTemp = patientService.searchUsername(text);
        }else if(input.equals("1")){
            userTemp = patientService.searchEmail(text);
        }else if(input.equals("2")){
            userTemp = patientService.searchDNI(text);
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

    @GetMapping({"/buscarSanitario"})
    public String healthPersonnelList(Model model, @RequestParam(name="q", required=false) String query) {
        Collection<HealthPersonnel> result = (query == null) ? healthPersonnelService.returnAllHealthPersonnels() : healthPersonnelService.search(query);
        model.addAttribute("healthPersonnelList",result);
       
        return "buscarSanitario";
    }

    /*@GetMapping("/searchHealthPersonnel")
    ModelAndView searchHealthPersonnel(@RequestParam Map<String,String> requestParams) {
        String input=requestParams.get("input");
        String text=requestParams.get("username");
        HealthPersonnel userTemp;

        if(input.equals("0")){
            userTemp = healthPersonnelService.searchUsername(text);
        }else if(input.equals("1")){
            userTemp = healthPersonnelService.searchEmail(text);
        }else if(input.equals("2")){
            userTemp = healthPersonnelService.searchDni(text);
        }else{
            throw new IncorrectSearchParametersException();
        }
        if(userTemp!=null){
            var mv = new ModelAndView("mostrar");
            mv.addObject("user", userTemp);
            return mv;
        }
        throw new UserNotFoundException(text);
    }*/

    //Call to the exception
    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView exception(UserNotFoundException e){
        var mv = new ModelAndView("user-not-found");
        mv.addObject("username",e.getUsername());
        return mv;
    }

    @ExceptionHandler(IncorrectSearchParametersException.class)
    public ModelAndView exception2(IncorrectSearchParametersException e){
        return new ModelAndView("incorrect-parameters");
    }
    @GetMapping("/chooseDoctors")
    public ModelAndView chooseDoc(@RequestParam(required = false)  String id){
        if (id != null){
            var mv = new ModelAndView("choose-doctor");
            mv.addObject("doctors",healthPersonnelService.returnAllHealthPersonnels());
            mv.addObject("id", Long.parseLong(id) - 1);
            return mv;
        }else{
            return new ModelAndView("index");
        }
    }
}
