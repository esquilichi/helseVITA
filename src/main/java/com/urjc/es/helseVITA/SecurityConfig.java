package com.urjc.es.helseVITA;

import com.urjc.es.helseVITA.Services.PatientDetailsServiceImpl;
import com.urjc.es.helseVITA.Enums.EnumRolUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PatientDetailsServiceImpl patientDetailsService;

    public EnumRolUsers rol;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(); 
        /* auth.inMemoryAuthentication().withUser("paciente").password(encoder.encode("{noop}patientPass")).roles("ROLE_PATIENT");
        auth.inMemoryAuthentication().withUser("healthPersonnel").password(encoder.encode("{noop}healthPersonnelPass")).roles("ROLE_HEALTHPERSONNEL");
        auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("{noop}adminPass")).roles("ROLE_ADMIN"); */
        auth.userDetailsService(userDetailsService());
    }
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(); 
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("patient")
                        .password(encoder.encode("{noop}patientPass"))
                        .roles("ROLE_PATIENT")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //públicas
                .authorizeRequests()
                    .antMatchers("/index", "/login", "/", "/loginError", "/logOut", 
                        "/contact-us", "/faq", "/myHelsevita", "/search-center", "/work-with-us", "/error", "/user-not-found")   //Aquí se ponen las rutas que se permiten a ese rol (Anónimo en este caso)     
                    .permitAll()
                    //privadas
                    .antMatchers("").hasAnyRole("ROLE_HEALTHPERSONNEL") //Páginas permitidas para HealthPersonnel
                    .antMatchers("/areaPaciente", "/citaAgregada", "").hasAnyRole("ROLE_PATIENT") //Páginas permitidas para Paciente
                    .antMatchers("").hasAnyRole("ROLE_ADMIN") //Páginas permitidas para Admin
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
