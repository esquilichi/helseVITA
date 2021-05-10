package com.urjc.es.helseVITA.Services;

import java.util.Optional;

import com.urjc.es.helseVITA.Entities.Patient;
import com.urjc.es.helseVITA.Repositories.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class PatientDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private PatientRepository patientRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username){ //Construir usuario

        Optional <Patient> patient = patientRepository.findByUsername(username);
        Patient temp = new Patient();

        UserBuilder builder = null;

        if (patient.isPresent()){
            builder = User.withUsername(username);
            builder.disabled(false);
			builder.password(temp.getPassword());
			builder.authorities(new SimpleGrantedAuthority("ROLE_PACIENTE"));

        }else{
            throw new UsernameNotFoundException("Usuario no encontrado");
		}
        return builder.build();
    }
    
}
