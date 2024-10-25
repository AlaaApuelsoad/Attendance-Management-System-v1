package com.demo.Attendance.controller;

import com.demo.Attendance.dto.LoginDto.LoginDto;
import com.demo.Attendance.jwtSecurity.JwtService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public HomeController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("login")
    public String login(@RequestBody LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(loginDto);
        }
        return "fail";
    }

    @GetMapping("hello")
    @PreAuthorize("hasRole('ADMIN')")
    public String hello(){
        return "hello Page";
    }

    @GetMapping("welcome")
    @PreAuthorize("hasAuthority('STUDENT')")
    public String welcome(){
        return "welcome";
    }


}
