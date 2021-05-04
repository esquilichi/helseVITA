package com.urjc.es.helseVITA;

import com.urjc.es.helseVITA.Services.PatientDetailsServiceImpl;
import com.urjc.es.helseVITA.Enums.EnumRolUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PatientDetailsServiceImpl patientDetailsService;

    public EnumRolUsers rol;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/index")   //Aquí se ponen las rutas que se permiten a ese rol             
                    .permitAll()
                    .and()
                .formLogin()
                    .loginPage("/login") //Ruta login
                    .defaultSuccessUrl("/index") //Página a la que nos lleva al hacer el login
                    .loginProcessingUrl("") //Controlador de login
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("")//Url para deslogearse
                    .logoutSuccessUrl("/index"); //Url de la zona pública
            
        http.csrf().disable();
        http.headers().frameOptions().disable();//Para poder acceder a la consola de h2

}
}
