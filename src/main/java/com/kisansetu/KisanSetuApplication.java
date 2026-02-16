package com.kisansetu;

import com.kisansetu.entity.Farmer;
import com.kisansetu.repository.FarmerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class KisanSetuApplication {

	public static void main(String[] args) {
		SpringApplication.run(KisanSetuApplication.class, args);
	}

}