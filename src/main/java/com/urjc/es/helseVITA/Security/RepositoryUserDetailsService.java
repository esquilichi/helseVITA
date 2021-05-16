package com.urjc.es.helseVITA.Security;

import java.util.ArrayList;
import java.util.List;

import com.urjc.es.helseVITA.Entities.Admin;
import com.urjc.es.helseVITA.Entities.HealthPersonnel;
import com.urjc.es.helseVITA.Entities.Patient;
import com.urjc.es.helseVITA.Repositories.AdminRepository;
import com.urjc.es.helseVITA.Repositories.HealthPersonnelRepository;
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
    @Autowired
    private HealthPersonnelRepository healthPersonnelRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient patient = patientRepository.findByUsername(username).orElse(null);
        HealthPersonnel healthPersonnel = healthPersonnelRepository.findHealthPersonnelByUsername(username).orElse(null);
        Admin admin = adminRepository.findByUsername(username);
        if (patient != null) {
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority("ROLE_PATIENT"));
            return new org.springframework.security.core.userdetails.User(patient.getUsername(), patient.getPassword(), roles);
        }
        if (healthPersonnel != null){
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority("ROLE_HEALTHPERSONNEL"));
            return new org.springframework.security.core.userdetails.User(healthPersonnel.getUsername(), healthPersonnel.getPassword(), roles);
        }
        if (admin != null){
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new org.springframework.security.core.userdetails.User(admin.getUsername(),admin.getPassword(),roles);
        }
        return null;
    }
    
}
