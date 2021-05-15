package com.urjc.es.helseVITA.Security;

import java.security.SecureRandom;

import com.urjc.es.helseVITA.Enums.EnumRolUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    

    @Autowired
    public RepositoryUserDetailsService userDetailsService;


    public EnumRolUsers rol;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12, new SecureRandom());
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()

                    .antMatchers("/index", "/login", "/", "/loginError", "/logOut",

                        "/contact-us", "/faq", "/myHelsevita", "/search-center", "/work-with-us", "/error", "/user-not-found")   //Aquí se ponen las rutas que se permiten a ese rol (Anónimo en este caso)     

                    .permitAll()

                    //privadas

                    .antMatchers("/areaAdmin", "/areaPaciente", "/areaSanitario", "/crearPaciente", "/insurance", "/crearSanitario", "/mostrarPacientes", "/mostrarSanitario").hasAnyRole("ADMIN") //Páginas permitidas para HealthPersonnel

                    .antMatchers("/areaPaciente", "/citaAgregada", "/insurance").hasAnyRole("PATIENT") //Páginas permitidas para Paciente

                .antMatchers("/areaPaciente", "/citaAgregada", "/insurance").hasAnyRole("PATIENT") //Páginas permitidas para Paciente

                .antMatchers("/areaPaciente", "/areaSanitario", "/crearPaciente", "/asignarNuevoPaciente", "/asignarNuevosanitario",

                        "/buscarPaciente", "/buscarSanitario", "/crearSanitario", "/mostrar/**", "/mostrarPacientes", "/mostrarSanitario").hasAnyRole("ADMIN","PATIENT","HEALTHPERSONNEL"); //Páginas permitidas para Admin
                   
                http
                	.formLogin()
                    .loginPage("/login") //Ruta login
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/loginExitoso") //Página a la que nos lleva al hacer el login
                    .failureUrl("/loginError")
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/logOut")//Url para deslogearse
                    .logoutSuccessUrl("/index") //Url de la zona pública
                    .and()
                .headers().frameOptions().disable();//Para poder acceder a la consola de h2

            http
                    .headers()
                    .xssProtection();

            http.httpBasic();
            
    }
}
