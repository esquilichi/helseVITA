package com.urjc.es.helseVITA.Security;

import com.urjc.es.helseVITA.Entities.HealthPersonnel;
import com.urjc.es.helseVITA.Repositories.HealthPersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HealthPersonnelRepositoryAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    HealthPersonnelRepository healthPersonnelService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<HealthPersonnel> op = healthPersonnelService.findHealthPersonnelByUsername(authentication.getName());
        var healthPersonnel = op.orElse(null);
        String password = (String) authentication.getCredentials();
        if (healthPersonnel == null || !new BCryptPasswordEncoder().matches(password, healthPersonnel.getPassword())){
            throw new BadCredentialsException("Password provided does not match");
        }
        List<GrantedAuthority> roles = new ArrayList<>();

        roles.add(new SimpleGrantedAuthority(healthPersonnel.getRol().toString()));
        return new UsernamePasswordAuthenticationToken(healthPersonnel.getUsername(),password,roles);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
