package com.urjc.es.helseVITA.Services;

import com.urjc.es.helseVITA.Entities.Admin;
import com.urjc.es.helseVITA.Repositories.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;
    
    public Admin addAdmin(Admin admin){
        return adminRepository.save(admin);
        }
}

