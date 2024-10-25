package com.demo.Attendance.configuration;

import com.demo.Attendance.jwtSecurity.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Enable method security
public class SecurityConfig {

    private UserDetailsService userDetailsService;
    private JwtFilter jwtFilter;


    public SecurityConfig(UserDetailsService userDetailsService, JwtFilter jwtFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());

        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("login","register/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();
    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
////                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//
//                        // Admin REST APIs Endpoints
//                        .requestMatchers(HttpMethod.GET,"/api/v1/admins/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST,"/api/v1/admins").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PATCH,"/api/v1/admins/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE,"/api/v1/admins/**").hasRole("ADMIN")
//
//                        // Roles REST APIs Endpoints
//                        .requestMatchers(HttpMethod.GET,"/api/v1/Roles/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST,"/api/v1/Roles").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.PATCH,"/api/v1/Roles/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE,"/api/v1/Roles/**").hasAnyRole("ADMIN")
//
//                        // Student REST APIs Endpoints
//                        .requestMatchers(HttpMethod.GET,"/api/v1/students/**").hasAnyRole("ADMIN","STUDENT")
//                        .requestMatchers(HttpMethod.POST,"/api/v1/students").hasAnyRole("ADMIN","STUDENT")
//                        .requestMatchers(HttpMethod.PATCH,"/api/v1/students/**").hasAnyRole("ADMIN","STUDENT")
//                        .requestMatchers(HttpMethod.DELETE,"/api/v1/students/**").hasRole("ADMIN")
//
//                        // Instructor REST APIs Endpoints
//                        .requestMatchers(HttpMethod.GET,"/api/v1/instructors/**").hasAnyRole("ADMIN","INSTRUCTOR")
//                        .requestMatchers(HttpMethod.POST,"/api/v1/instructors").hasAnyRole("ADMIN","INSTRUCTOR")
//                        .requestMatchers(HttpMethod.PATCH,"/api/v1/instructors/**").hasAnyRole("ADMIN","INSTRUCTOR")
//                        .requestMatchers(HttpMethod.DELETE,"/api/v1/instructors/**").hasAnyRole("ADMIN")
//
//                        // Course REST APIs Endpoints
//                        .requestMatchers(HttpMethod.GET,"/api/v1/courses/**").hasAnyRole("ADMIN","INSTRUCTOR")
//                        .requestMatchers(HttpMethod.POST,"/api/v1/courses").hasAnyRole("ADMIN","INSTRUCTOR")
//                        .requestMatchers(HttpMethod.PATCH,"/api/v1/courses/**").hasAnyRole("ADMIN","INSTRUCTOR")
//                        .requestMatchers(HttpMethod.DELETE,"/api/v1/courses/**").hasAnyRole("ADMIN","INSTRUCTOR")
//
//                        // Enrollment REST APIs Endpoints
//                        .requestMatchers(HttpMethod.GET,"/api/v1/enrollments/**").hasAnyRole("ADMIN","STUDENT")
//                        .requestMatchers(HttpMethod.POST,"/api/v1/enrollments/**").hasAnyRole("ADMIN","STUDENT")
//
//                        // Attendance REST APIs Endpoints
//                        .requestMatchers(HttpMethod.GET,"/api/v1/attendance/**").hasAnyRole("ADMIN","STUDENT","INSTRUCTOR")
//                        .requestMatchers(HttpMethod.POST,"/api/v1/attendance/**").hasAnyRole("ADMIN","INSTRUCTOR")
//
//
//                        .anyRequest().authenticated()
//
//                )
//                .csrf(AbstractHttpConfigurer::disable)
//                .userDetailsService(userDetailsService)
//                .httpBasic(Customizer.withDefaults());
//        return httpSecurity.build();
//    }


}
