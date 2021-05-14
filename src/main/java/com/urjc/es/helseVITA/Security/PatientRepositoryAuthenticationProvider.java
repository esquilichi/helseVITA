package com.urjc.es.helseVITA.Security;

import com.urjc.es.helseVITA.Entities.Patient;
import com.urjc.es.helseVITA.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientRepositoryAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    PatientRepository patientRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<Patient> op = patientRepository.findByUsername(authentication.getName());
        var patient = op.orElse(null);
        String password = (String) authentication.getCredentials();
        if (patient == null || !new BCryptPasswordEncoder().matches(password, patient.getPassword())){
            throw new BadCredentialsException("Password provided does not match");
        }
        List<GrantedAuthority> roles = new ArrayList<>();

        roles.add(new SimpleGrantedAuthority(patient.getRol().toString()));
        return new UsernamePasswordAuthenticationToken(patient.getUsername(),password,roles);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        //No se que es esto, pero creo que no hay que implementarlo para DWS ;)
        return true;
    }
}