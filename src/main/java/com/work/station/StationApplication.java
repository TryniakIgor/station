package com.work.station;

import com.work.station.model.Role;
import com.work.station.model.User;
import com.work.station.repository.UserRepo;
import com.work.station.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class StationApplication {

	public static void main(String[] args) {
		SpringApplication.run(StationApplication.class, args);
	}
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner run(UserService userService, UserRepo userRepo) {
        return args -> {
            userService.saveUser(new User(null, "john", "1111", false, new ArrayList<>()));
        };

    }
}
