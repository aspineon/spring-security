package com.mimaraslan.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mimaraslan.model.User;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
	
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Delete all
        this.userRepository.deleteAll();

        // Crete users
        User admin = new User("admin",passwordEncoder.encode("admin"),"ADMIN","ACCESS_TEST1,ACCESS_TEST2");
        User katerina = new User("katerina",passwordEncoder.encode("katerina"),"USER","");
        User manager = new User("manager",passwordEncoder.encode("manager"),"MANAGER","ACCESS_TEST1");

        List<User> users = Arrays.asList(admin,katerina,manager);

        // Save to db
        this.userRepository.saveAll(users);
    }
}