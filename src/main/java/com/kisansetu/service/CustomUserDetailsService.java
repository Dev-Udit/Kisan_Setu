package com.kisansetu.service;

import com.kisansetu.entity.Farmer;
import com.kisansetu.repository.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private FarmerRepository farmerRepository;

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        Farmer farmer = farmerRepository.findByMobile(mobile)
                .orElseThrow(() -> new UsernameNotFoundException("Farmer not found"));

        String role = mobile.equals("0000") ? "ADMIN" : "FARMER";

        return User.withUsername(farmer.getMobile())
                .password(farmer.getPassword())
                .roles(role)
                .build();
    }
}