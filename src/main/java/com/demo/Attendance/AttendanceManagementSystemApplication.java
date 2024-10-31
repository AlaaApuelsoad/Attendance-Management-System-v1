package com.demo.Attendance;

import com.demo.Attendance.serviceImplementation.AdminServiceImpl;
import com.demo.Attendance.serviceImplementation.InstructorServiceImpl;
import com.demo.Attendance.serviceImplementation.StudentServiceImp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@EnableAspectJAutoProxy
public class AttendanceManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttendanceManagementSystemApplication.class, args);

    }

    private final AdminServiceImpl adminService;
    private final InstructorServiceImpl instructorService;
    private final StudentServiceImp studentService;
    private final EmailService emailService;


    public AttendanceManagementSystemApplication(AdminServiceImpl adminService, InstructorServiceImpl instructorService,
                                                 StudentServiceImp studentService, EmailService emailService) {
        this.adminService = adminService;
        this.instructorService = instructorService;
        this.studentService = studentService;
        this.emailService = emailService;
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {

//            adminService.init();
//            instructorService.init();
//            studentService.init();
                emailService.sendEmail();
            System.out.println("Welcome to Attendance Management System");
        };
    }


}
