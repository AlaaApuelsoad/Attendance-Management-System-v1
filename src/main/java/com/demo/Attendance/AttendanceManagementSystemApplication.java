package com.demo.Attendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AttendanceManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceManagementSystemApplication.class, args);

	}


}
