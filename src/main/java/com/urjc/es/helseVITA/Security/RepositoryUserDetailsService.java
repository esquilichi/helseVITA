package com.urjc.es.helseVITA.Security;

import java.util.ArrayList;
import java.util.List;

import com.urjc.es.helseVITA.Entities.Patient;
import com.urjc.es.helseVITA.Repositories.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class RepositoryUserDetailsService implements UserDetailsService{

    @Autowired
    private PatientRepository patientRepository;

    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient patient = patientRepository.findByUsername(username).orElse(null);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_PATIENT"));
        return new org.springframework.security.core.userdetails.User(patient.getUsername(), patient.getPassword(), roles);
    }
    
}
