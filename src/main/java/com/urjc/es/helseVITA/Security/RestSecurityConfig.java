package com.urjc.es.helseVITA.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    public UserRepositoryAuthenticationProvider userRepoAuthProvider;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/api/**");
        http
            .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/login").authenticated()
                //Patients
                .antMatchers(HttpMethod.GET, "/api/patients/{id}/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/patients/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/patients/{id}**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/patients/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/patients/{id}**").hasRole("ADMIN")
                //HealthPersonnels
                .antMatchers(HttpMethod.DELETE, "/api/healthPersonnels/{id}/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/healthPersonnels/{id}/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/healthPersonnels/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/healthPersonnels/{id}/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/healthPersonnels/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/healthPersonnels/{id}/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/healthPersonnels/{id}/**").hasRole("ADMIN")
                //Appointments
                .antMatchers(HttpMethod.GET, "/api/appointments/{id}/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/appointments/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/appointments/{id}/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/appointments/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/appointments/{id}/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/appointments/{id}/**").hasRole("ADMIN")
            .anyRequest().permitAll();
        http.csrf().disable();
        http.httpBasic();
        http.logout().logoutSuccessHandler((rq, rs, a) -> { });
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userRepoAuthProvider);
    }

}