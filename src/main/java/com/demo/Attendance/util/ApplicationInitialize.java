package com.demo.Attendance.util;

import com.demo.Attendance.model.Role;
import com.demo.Attendance.model.User;
import com.demo.Attendance.repository.RoleRepository;
import com.demo.Attendance.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class ApplicationInitialize {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationInitialize.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationInitialize(UserRepository userRepository, RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostConstruct
    @Transactional
    public void initialize() {

        // Create and save roles
        List<String> roleNames = Arrays.asList("ROLE_ADMIN", "STUDENT", "INSTRUCTOR");
        roleNames.forEach(roleName -> {
            if (roleRepository.findByRoleName(roleName) == null) {
                Role role = new Role();
                role.setRoleName(roleName);
                roleRepository.save(role);
            }
        });

        // Create and save Main User
        Role adminRole = roleRepository.findByRoleName("ROLE_ADMIN");
        if (adminRole != null) {
            if (userRepository.findByUserName("admin") == null) {
                User mainUser = new User();
                mainUser.setUserName("admin");
                mainUser.setPassword(passwordEncoder.encode("admin123"));
                mainUser.setRole(adminRole);
                userRepository.save(mainUser);
                logger.info("Main user 'admin' created.");
            }
        }

    }
}
