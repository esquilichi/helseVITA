package com.urjc.es.helseVITA.Security;

import com.urjc.es.helseVITA.Services.PatientDetailsServiceImpl;
import com.urjc.es.helseVITA.Enums.EnumRolUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    

    @Autowired
    public PatientRepositoryAuthenticationProvider authenticationProvider;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PatientDetailsServiceImpl patientDetailsService;

    public EnumRolUsers rol;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //públicas
                .authorizeRequests()
                    .antMatchers("/index.html", "/login", "/", "/loginError", "/logOut",
                        "/contact-us", "/faq", "/myHelsevita", "/search-center", "/work-with-us", "/error", "/user-not-found")   //Aquí se ponen las rutas que se permiten a ese rol (Anónimo en este caso)     
                    .permitAll()
                    //privadas
                    .antMatchers("/areaSanitario").hasAnyRole("ROLE_HEALTHPERSONNEL") //Páginas permitidas para HealthPersonnel
                    .antMatchers("/areaPaciente", "/citaAgregada", "/insurance").hasAnyRole("ROLE_PATIENT") //Páginas permitidas para Paciente
                    .antMatchers("/areaPaciente", "/areaSanitario", "/crearPaciente.html", "/asignarNuevoPaciente", "/asignarNuevosanitario",
                        "/buscarPaciente.html", "/buscarSanitario.html", "/crearSanitario", "/mostrar/**", "/mostrarPacientes", "/mostrarSanitario").hasAnyRole("ROLE_HEALTHPERSONNEL","ROLE_PATIENT","ROLE_ADMIN") //Páginas permitidas para Admin
                    .and()
                    
                .formLogin()
                    .loginPage("/login") //Ruta login
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/index") //Página a la que nos lleva al hacer el login
                    .failureUrl("/loginError")
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/logOut")//Url para deslogearse
                    .logoutSuccessUrl("/index") //Url de la zona pública
                    .and()
                .csrf().disable()  
                .headers().frameOptions().disable();//Para poder acceder a la consola de h2
    }
}
