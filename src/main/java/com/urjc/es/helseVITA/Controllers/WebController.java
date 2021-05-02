package com.urjc.es.helseVITA.Controllers;

import com.urjc.es.helseVITA.Entities.Appointment;
import com.urjc.es.helseVITA.Entities.HealthPersonnel;
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

import java.util.*;

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
/* 

    @RequestMapping("/viewPatients")
    ModelAndView viewPatients() {
        var mv = new ModelAndView("mostrarPacientes");
        mv.addObject("users", patientService.returnAllPatients());
        return mv;
    }

    @RequestMapping("/viewHealthPersonnel")
    ModelAndView viewHealthPersonnel() {
        var mv = new ModelAndView("mostrarSanitario");
        mv.addObject("users", healthPersonnelService.returnAllHealthPersonnels());
        return mv;
    } */

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
    @GetMapping({"/searchPatient"})
    public String patientList(Model model, @RequestParam(name = "q1", required = false) String query, @RequestParam(name = "q2",required = false) String query2) {
        boolean b1 = false;
        boolean b2 = false;
        List<Patient> result = null;
        List<Patient> result2 = null;
        List<Patient> mi_lista;
        if (query != null){
            result = (List<Patient>) patientService.search(query);
            b1 = true;
        }
        if (query2 != null){
            result2 = patientService.searchByAge(query2);
            b2 = true;
        }
        if (b1 && b2){
            mi_lista = intersection(result, result2);
        } else if (b1){
            mi_lista = result;
        }else if (b2){
            mi_lista = result2;
        }else{
            mi_lista = (List<Patient>) patientService.returnAllPatients();
        }

        if (result2 == null){
            mi_lista = result;
        }
        model.addAttribute("object", mi_lista);
        return "buscarPaciente";
    }

    @GetMapping({"/searchHealthPersonnel"})
    public String healthPersonnelList(Model model, @RequestParam(name = "q", required = false) String query) {
        Collection<HealthPersonnel> result = (query == null) ? healthPersonnelService.returnAllHealthPersonnels() : healthPersonnelService.search(query);
        model.addAttribute("object", result);
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
    public ModelAndView exception(UserNotFoundException e) {
        var mv = new ModelAndView("user-not-found");
        mv.addObject("username", e.getUsername());
        return mv;
    }

    @ExceptionHandler(IncorrectSearchParametersException.class)
    public ModelAndView exception2(IncorrectSearchParametersException e) {
        return new ModelAndView("incorrect-parameters");
    }
/* 
    @GetMapping("/chooseDoctors")
    public ModelAndView chooseDoc(@RequestParam(required = false) String id) {
        if (id != null) {
            var mv = new ModelAndView("choose-doctor");
            mv.addObject("doctors", healthPersonnelService.returnAllHealthPersonnels());
            mv.addObject("id", Long.parseLong(id) - 1);
            return mv;
        } else {
            return new ModelAndView("index");
        }
    } */

    @GetMapping("/addAppointment/{id}")
    public ModelAndView addAppointment(Model model, @PathVariable Integer id) {
        var temp = patientService.returnPatient(id);
        var mv = new ModelAndView("appointment");
        mv.addObject("paciente", temp);
        return mv;
    }

    @RequestMapping("/whichDoc")
    public ModelAndView addAppointmentCode(@RequestParam Map<String,String> requestParams){
        var id_paciente = Integer.parseInt(requestParams.get("id_paciente"));
        var text = requestParams.get("tiempo");
        int year = Integer.parseInt((String) text.subSequence(0,4));
        int month = Integer.parseInt((String) text.subSequence(5,7));
        int day = Integer.parseInt((String) text.subSequence(8,10));
        int hour = Integer.parseInt((String) text.subSequence(11,13));
        int minute = Integer.parseInt((String) text.subSequence(14,16)); //Es inútil, no hay atributo minutos, pero no me quiteis la ilusión...

        var paciente = patientService.returnPatient(id_paciente);
        Appointment temp = new Appointment(hour,minute, day,month,year,paciente);

        List<Appointment> citas = paciente.getAppointments();
        citas.add(temp);
        paciente.setAppointments(citas);
        patientService.addPatient(paciente);
        List<HealthPersonnel> lista = paciente.getHealthPersonnelList();

        var mv = new ModelAndView("cualDoctor");
        mv.addObject("cita", temp);
        mv.addObject("docs",lista);
        mv.addObject("paciente",paciente);
        return mv;
    }

    

    public List<Patient> union(List<Patient> list1, List<Patient> list2) {
        if (!(list1 == null || list2 == null)){
            Set<Patient> set = new HashSet<Patient>();

            set.addAll(list1);
            set.addAll(list2);

            return new ArrayList<Patient>(set);
        }
        return null;
    }
    public List<Patient> intersection(List<Patient> list1, List<Patient> list2) {
        if (!(list1 == null || list2 == null)){
            List<Patient> list = new ArrayList<>();

            for (Patient t : list1) {
                if(list2.contains(t)) {
                    list.add(t);
                }
            }

            return list;
        }
        return null;
    }
}