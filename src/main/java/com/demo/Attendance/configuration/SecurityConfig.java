package com.demo.Attendance.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
//                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // Admin REST APIs Endpoints
                        .requestMatchers(HttpMethod.GET,"/api/v1/admins/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/v1/admins").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH,"/api/v1/admins/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/admins/**").hasRole("ADMIN")

                        // Roles REST APIs Endpoints
                        .requestMatchers(HttpMethod.GET,"/api/v1/Roles/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/v1/Roles").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH,"/api/v1/Roles/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/Roles/**").hasAnyRole("ADMIN")

                        // Student REST APIs Endpoints
                        .requestMatchers(HttpMethod.GET,"/api/v1/students/**").hasAnyRole("ADMIN","STUDENT")
                        .requestMatchers(HttpMethod.POST,"/api/v1/students").hasAnyRole("ADMIN","STUDENT")
                        .requestMatchers(HttpMethod.PATCH,"/api/v1/students/**").hasAnyRole("ADMIN","STUDENT")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/students/**").hasRole("ADMIN")

                        // Instructor REST APIs Endpoints
                        .requestMatchers(HttpMethod.GET,"/api/v1/instructors/**").hasAnyRole("ADMIN","INSTRUCTOR")
                        .requestMatchers(HttpMethod.POST,"/api/v1/instructors").hasAnyRole("ADMIN","INSTRUCTOR")
                        .requestMatchers(HttpMethod.PATCH,"/api/v1/instructors/**").hasAnyRole("ADMIN","INSTRUCTOR")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/instructors/**").hasAnyRole("ADMIN")

                        // Course REST APIs Endpoints
                        .requestMatchers(HttpMethod.GET,"/api/v1/courses/**").hasAnyRole("ADMIN","INSTRUCTOR")
                        .requestMatchers(HttpMethod.POST,"/api/v1/courses").hasAnyRole("ADMIN","INSTRUCTOR")
                        .requestMatchers(HttpMethod.PATCH,"/api/v1/courses/**").hasAnyRole("ADMIN","INSTRUCTOR")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/courses/**").hasAnyRole("ADMIN","INSTRUCTOR")

                        // Enrollment REST APIs Endpoints
                        .requestMatchers(HttpMethod.GET,"/api/v1/enrollments/**").hasAnyRole("ADMIN","STUDENT")
                        .requestMatchers(HttpMethod.POST,"/api/v1/enrollments/**").hasAnyRole("ADMIN","STUDENT")

                        // Attendance REST APIs Endpoints
                        .requestMatchers(HttpMethod.GET,"/api/v1/attendance/**").hasAnyRole("ADMIN","STUDENT","INSTRUCTOR")
                        .requestMatchers(HttpMethod.POST,"/api/v1/attendance/**").hasAnyRole("ADMIN","INSTRUCTOR")


                        .anyRequest().authenticated()

                )
                .csrf(csrf->csrf.disable())
                .userDetailsService(userDetailsService)
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }



}
