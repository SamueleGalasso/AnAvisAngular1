package com.anavis;

import com.anavis.config.SecurityUtility;
import com.anavis.domain.Bloodcount;
import com.anavis.domain.User;
import com.anavis.domain.security.Role;
import com.anavis.domain.security.UserRole;
import com.anavis.service.BloodcountService;
import com.anavis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AnAvisApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;
    @Autowired
    private BloodcountService bloodcountService;

    public static void main(String[] args) {
        SpringApplication.run(AnAvisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(bloodcountService.getBloodcount() == null) {
            Bloodcount bloodcount = new Bloodcount();
            bloodcountService.save(bloodcount);
        }


        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Adams");
        user1.setUsername("j");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
        user1.setEmail("JAdams@gmail.com");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId(1);
        role1.setName("ROLE_USER");
        userRoles.add(new UserRole(user1, role1));

        if(userService.createUser(user1, userRoles) == null){
            userService.save(user1);
        }

        userRoles.clear();

        User user2 = new User();
        user2.setFirstName("Admin");
        user2.setLastName("Admin");
        user2.setUsername("admin");
        user2.setPassword(SecurityUtility.passwordEncoder().encode("p"));
        user2.setEmail("Admin@gmail.com");
        Role role2 = new Role();
        role2.setRoleId(2);
        role2.setName("ROLE_ADMIN");
        userRoles.add(new UserRole(user2, role2));


        if(userService.createUser(user2, userRoles) == null){
            userService.save(user2);
        }
    }

}

