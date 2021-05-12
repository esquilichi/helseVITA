package com.urjc.es.helseVITA.Security;

import com.urjc.es.helseVITA.Entities.Patient;
import com.urjc.es.helseVITA.Repositories.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private PatientRepository patientRepository;
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
    Patient patient = patientRepository.findByUsername(auth.getName()).get();
    String password = (String) auth.getCredentials();

    if (patient == null || !new BCryptPasswordEncoder().matches(password, patient.getPasswordHash())) {
        throw new BadCredentialsException("Wrong credentials");
    }

    /* List<GrantedAuthority> roles = new ArrayList<>();  //No sé qué se hace aquí
    for (String role : patient.getRol()) {
        roles.add(new SimpleGrantedAuthority(role));
    } */
    //return new UsernamePasswordAuthenticationToken(patient.getName(), password, roles);
    return null;

}

    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        return false;
    }
}
