package com.urjc.es.helseVITA.Controllers;

import com.urjc.es.helseVITA.Entities.Appointment;
import com.urjc.es.helseVITA.Entities.HealthPersonnel;
import com.urjc.es.helseVITA.Entities.Patient;
import com.urjc.es.helseVITA.Enums.EnumRoles;
import com.urjc.es.helseVITA.Exceptions.*;
import com.urjc.es.helseVITA.Services.AppointmentService;
import com.urjc.es.helseVITA.Services.HealthPersonnelService;
import com.urjc.es.helseVITA.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import java.util.*;

@Controller
public class WebController {

    @Autowired
    PatientService patientService;

    @Autowired
    HealthPersonnelService healthPersonnelService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    EntityManager entityManager;

    Appointment appointmentToEngage;
    /*
    Si se necesita saber el usuario que está autenticado navegando al toque se hace
    SecurityContextHolder.getContext().getAuthentication();
    Esto nos da opcion a poder coger luego 5 cosas SI NO ES NULL, COMPROBAR
    1) getPrincipal(), el objeto entero, no interesa
    2) getCredentials(), password
    3) getAuthorities(), el rol que tiene
    4) getDetails(), más detalles como la ip, no interesa.
    Si se quiere enseñar una página tal cual, y luego enseñarla con cambios al que está logueado...
    Procedimiento: crear un plantilla para el que no está logueado (index) y cuando detectamos un logged user
    (SecurityContextHolder.getContext().getAuthentication() != null) llamamos a una plantilla preparada con mustache
    para poder enseñar ese username o lo que queramos. Ya sabiendo el username, podemos hacer query a BBDD para pedir
    los pacientes/docs asociados a ese username y mostrarlos :)
    Un besote
    Ismael de las 2:15 AM
     */
    @RequestMapping("/")
    ModelAndView index(HttpServletRequest request) {
        var a = SecurityContextHolder.getContext().getAuthentication();
        var authorities = a.getName();
        if (authorities == null){
            return new ModelAndView("index");
        }else {
            var mv = new ModelAndView("indexAuth");
            mv.addObject("user",authorities.toString());
            return mv;
        }

    }

    @GetMapping("/loginError")
    public String loginerror(HttpServletRequest request) {
        return "loginerror";
    }

    @RequestMapping("/mostrar/{id}")
    ModelAndView view(@PathVariable Integer id,HttpServletRequest request) {
        Patient temp = patientService.returnPatient(id);
        var mv = new ModelAndView("mostrar");
        mv.addObject("user", temp);
        return mv;
    }

    @GetMapping({"/searchPatient"})
    public String patientList(Model model, @RequestParam(name = "q1", required = false) String query, @RequestParam(name = "q2",required = false) String query2,HttpServletRequest request) {
        boolean b1 = false;
        boolean b2 = false;
        List<Patient> result = null;
        List<Patient> result2 = null;
        List<Patient> mi_lista;
        if (query != null) {
            result = (List<Patient>) patientService.search(query);
            b1 = true;
        }
        if (query2 != null) {
            result2 = patientService.searchByAge(query2);
            b2 = true;
        }
        if (b1 && b2) {
            mi_lista = intersectionP(result, result2);
        } else if (b1) {
            mi_lista = result;
        } else if (b2) {
            mi_lista = result2;
        } else {
            mi_lista = (List<Patient>) patientService.returnAllPatients();
        }

        if (result2 == null) {
            mi_lista = result;
        }
        model.addAttribute("object", mi_lista);
        return "buscarPaciente";
    }
    @GetMapping({"/searchHealthPersonnel"})
    public String healthPersonnelList(Model model, @RequestParam(name = "q1", required = false) String query, @RequestParam(name = "q2" , required = false) String query2,HttpServletRequest request) {
        boolean b1 = false;
        boolean b2 = false;
        List<HealthPersonnel> result = null;
        List<HealthPersonnel> result2 = null;
        List<HealthPersonnel> mi_lista;
        if (query != null){
            result = (List<HealthPersonnel>) healthPersonnelService.search(query);
            b1 = true;
        }
        if (query2 != null){
            result2 = healthPersonnelService.searchByAge(query2);
            b2 = true;
        }
        if (b1 && b2){
            mi_lista = intersectionH(result, result2);
        } else if (b1){
            mi_lista = result;
        }else if (b2){
            mi_lista = result2;
        }else{
            mi_lista = healthPersonnelService.returnAllHealthPersonnels();
        }

        if (result2 == null){
            mi_lista = result;
        }
        model.addAttribute("object", mi_lista);
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
    public ModelAndView exception(UserNotFoundException e,HttpServletRequest request) {
        var mv = new ModelAndView("user-not-found");
        mv.addObject("username", e.getUsername());
        return mv;
    }

    @ExceptionHandler(IncorrectSearchParametersException.class)
    public ModelAndView exception2(IncorrectSearchParametersException e,HttpServletRequest request) {
        return new ModelAndView("incorrect-parameters");
    }

    /*@ExceptionHandler(AppointmentNotFoundException.class)
    public ModelAndView exception3(AppointmentNotFoundException e, HttpServletRequest request) {

    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ModelAndView exception4(UserAlreadyExistsException e, HttpServletRequest request) {

    }*/

    @ExceptionHandler(AppointmentAlreadyExistsException.class)
    public ModelAndView exception5(AppointmentAlreadyExistsException e, HttpServletRequest request) {
        //var mv = new ModelAndView("appointmentAlreadyExists");
        //mv.addObject("day", e.get());
        return new ModelAndView("appointmentAlreadyExists");

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
    public ModelAndView addAppointment(Model model, @PathVariable Integer id,HttpServletRequest request) {

        var temp = patientService.returnPatient(id);
        var mv = new ModelAndView("appointment");
        mv.addObject("paciente", temp);
        return mv;
    }

    @RequestMapping("/whichDoc")
    public ModelAndView addAppointmentCode(@RequestParam Map<String,String> requestParams,HttpServletRequest request){
        var id_paciente = Integer.parseInt(requestParams.get("id_paciente"));
        var text = requestParams.get("tiempo");
        //var id_doctor = requestParams.get("id_doctor");
        int year = Integer.parseInt((String) text.subSequence(0,4));
        int month = Integer.parseInt((String) text.subSequence(5,7));
        int day = Integer.parseInt((String) text.subSequence(8,10));
        int hour = Integer.parseInt((String) text.subSequence(11,13));
        int minute = Integer.parseInt((String) text.subSequence(14,16)); 
        var paciente = patientService.returnPatient(id_paciente);

        List<Patient> lista_con_paciente = new ArrayList<>(); lista_con_paciente.add(paciente);
        Appointment temp = this.appointmentToEngage = new Appointment(hour,minute, day,month,year,null,paciente);

        if(paciente.getAppointments().contains(temp)) {
            throw new AppointmentAlreadyExistsException(temp);
        }

        //List<Appointment> citas = paciente.getAppointments();
        //citas.add(temp);
        //paciente.setAppointments(citas);
        //patientService.addPatient(paciente);
        //TypedQuery<HealthPersonnel> q1 = entityManager.createQuery("SELECT c FROM health_personnel c where c.id in (select health_personnel_list_id from patient_health_personnel_list where id = :id_paciente)",HealthPersonnel.class);
        //q1.setParameter("id_paciente",id_paciente);
        //var lista = q1.getResultList();
        var lista = healthPersonnelService.returnHealthPersonnelsByPatient(lista_con_paciente);
        var mv = new ModelAndView("cualDoctor");
        mv.addObject("cita", temp);
        mv.addObject("docs",lista);
        mv.addObject("paciente",paciente);
        return mv;
    }

    @RequestMapping("/exito")
    public ModelAndView exito(@RequestParam Map<String,String> requestParams,HttpServletRequest request){
        int id_doctor = Integer.parseInt(requestParams.get("id_doctor"));
        int id_paciente = Integer.parseInt(requestParams.get("id_paciente"));
        var paciente  = patientService.returnPatient(id_paciente);
        var doctor = healthPersonnelService.returnHealthPersonnel(id_doctor);
        this.appointmentToEngage.setHealthPersonnel(doctor);

        List<Appointment> ap_patient = patientService.addAppointmentToPatient(id_paciente,this.appointmentToEngage);
        //List<Appointment> ap_doctor = healthPersonnelService.addAppointmentToHealthPersonnel(id_doctor,this.appointmentToEngage);

        var mv = new ModelAndView("exito");

        return mv;
    }

    @RequestMapping("/crearSanitario")
    public ModelAndView crearSanitario(HttpServletRequest request){
        List<String> cosas = new ArrayList<>();
        var lista = EnumRoles.VALUES;
        for(EnumRoles c : EnumRoles.values())
            cosas.add(c.toString());

        var mv = new ModelAndView("crearSanitario");
        mv.addObject("roles",lista);
        return mv;
    }

    public List<Patient> union(List<Patient> list1, List<Patient> list2,HttpServletRequest request) {
        if (!(list1 == null || list2 == null)){
            Set<Patient> set = new HashSet<Patient>();

            set.addAll(list1);
            set.addAll(list2);

            return new ArrayList<Patient>(set);
        }
        return null;
    }
    public List<Patient> intersectionP(List<Patient> list1, List<Patient> list2) {
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

    public List<HealthPersonnel> intersectionH(List<HealthPersonnel> list1, List<HealthPersonnel> list2) {
        if (!(list1 == null || list2 == null)){
            List<HealthPersonnel> list = new ArrayList<>();

            for (HealthPersonnel t : list1) {
                if(list2.contains(t)) {
                    list.add(t);
                }
            }
            return list;
        }
        return null;
    }
    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model){
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("token", token.getToken());
        return "login";
    }

    @RequestMapping("/autenticacion")
    public ModelAndView autenticacion(@RequestParam String username, @RequestParam String password,HttpServletRequest request){
        Patient temp = patientService.returnPatientByUsername(username);
        if (new BCryptPasswordEncoder().matches(password,temp.getPassword())){

            return new ModelAndView("exito");
        }
        return new ModelAndView("error");
    }

}