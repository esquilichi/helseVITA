package com.urjc.es.helseVITA;

import com.urjc.es.helseVITA.Services.PatientDetailsServiceImpl;
import com.urjc.es.helseVITA.Enums.EnumRolUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
<<<<<<< Updated upstream:src/main/java/com/urjc/es/helseVITA/SecurityConfig.java
=======
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
>>>>>>> Stashed changes:src/main/java/com/urjc/es/helseVITA/Security/SecurityConfig.java
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
<<<<<<< Updated upstream:src/main/java/com/urjc/es/helseVITA/SecurityConfig.java
=======
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
>>>>>>> Stashed changes:src/main/java/com/urjc/es/helseVITA/Security/SecurityConfig.java
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    

    @Autowired
    public UserRepositoryAuthenticationProvider authenticationProvider;

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
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
<<<<<<< Updated upstream:src/main/java/com/urjc/es/helseVITA/SecurityConfig.java
        http
                //públicas
                .authorizeRequests()
                    .antMatchers("/index", "/login", "/", "/loginError", "/logOut", 
=======
    	http
            .csrf().disable()
            .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/login").permitAll()
                    .anyRequest().authenticated();

                    /* .antMatchers("/index", "/login", "/", "/loginError", "/logOut",

>>>>>>> Stashed changes:src/main/java/com/urjc/es/helseVITA/Security/SecurityConfig.java
                        "/contact-us", "/faq", "/myHelsevita", "/search-center", "/work-with-us", "/error", "/user-not-found")   //Aquí se ponen las rutas que se permiten a ese rol (Anónimo en este caso)     
                    .permitAll()
                    //privadas
<<<<<<< Updated upstream:src/main/java/com/urjc/es/helseVITA/SecurityConfig.java
                    .antMatchers("/areaSanitario").hasAnyRole("ROLE_HEALTHPERSONNEL") //Páginas permitidas para HealthPersonnel
                    .antMatchers("/areaPaciente", "/citaAgregada", "/insurance").hasAnyRole("ROLE_PATIENT") //Páginas permitidas para Paciente
                    .antMatchers("/areaPaciente", "/areaSanitario", "/crearPaciente", "/asignarNuevoPaciente", "/asignarNuevosanitario",
                        "/buscarPaciente", "/buscarSanitario", "/crearSanitario", "/mostrar", "/mostrarPacientes", "/mostrarSanitario").hasAnyRole("ROLE_ADMIN") //Páginas permitidas para Admin
                    .and()
                    
                .formLogin()
=======

                    .antMatchers("/areaSanitario.html").hasAnyRole("HEALTHPERSONNEL") //Páginas permitidas para HealthPersonnel

                    .antMatchers("/areaPaciente.html", "/citaAgregada.html", "/insurance").hasAnyRole("PATIENT") //Páginas permitidas para Paciente

                    .antMatchers("/areaPaciente.html", "/areaSanitario.html", "/crearPaciente.html", "/asignarNuevoPaciente", "/asignarNuevosanitario",

                        "/buscarPaciente", "/buscarSanitario", "/crearSanitario.html", "/mostrar/**", "/mostrarPacientes", "/mostrarSanitario").hasAnyRole("ADMIN","PATIENT","HEALTHPERSONNEL"); //Páginas permitidas para Admin
                    */
                /* http
                	.formLogin()
>>>>>>> Stashed changes:src/main/java/com/urjc/es/helseVITA/Security/SecurityConfig.java
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
                    .and() */
                
                
            http.headers().frameOptions().disable();//Para poder acceder a la consola de h2

}
}
