package com.demo.Attendance.aop;

import com.demo.Attendance.dtoAdmin.AdminRequestDto;
import com.demo.Attendance.dtoInstructor.InstructorRequestDto;
import com.demo.Attendance.dtoStudent.StudentRequestDto;
import com.demo.Attendance.util.UniqueChecker;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
public class UniqueCheckerAspect {

    private final UniqueChecker uniqueChecker;

    @Autowired
    public UniqueCheckerAspect(UniqueChecker uniqueChecker) {
        this.uniqueChecker = uniqueChecker;
    }

//    @Before("execution(* com.demo.Attendance.serviceImplementation.*.create*(..)) ")
    public void beforeCreateService(JoinPoint joinPoint) {

        System.out.println("Before any create method in ServiceImplementation");

        Object[] args = joinPoint.getArgs();

        if (args.length > 0 && (args[0] instanceof AdminRequestDto) ||
                args[0] instanceof StudentRequestDto ||
                args[0] instanceof InstructorRequestDto) {

            // Extract the DTO
            Object dto = args[0];

            // Perform unique checks based on the DTO type
            if (dto instanceof AdminRequestDto adminRequestDto) {
                uniqueChecker.emailUniqueChecker(adminRequestDto.getEmail());
                uniqueChecker.phoneNumberUniqueChecker(adminRequestDto.getPhoneNumber());
            }
            if (dto instanceof StudentRequestDto studentRequestDto) {
                uniqueChecker.emailUniqueChecker(studentRequestDto.getEmail());
                uniqueChecker.phoneNumberUniqueChecker(studentRequestDto.getPhoneNumber());
            }
            if (dto instanceof InstructorRequestDto instructorRequestDto) {
                uniqueChecker.emailUniqueChecker(instructorRequestDto.getEmail());
                uniqueChecker.phoneNumberUniqueChecker(instructorRequestDto.getPhoneNumber());
            }
        }

        System.out.println("Method: " + joinPoint.getSignature().getName());
        System.out.println("Arguments: " + Arrays.toString(args));
    }


//    @Before("execution(* com.demo.Attendance.serviceImplementation.*.update*(..))")
    public void beforeUpdateService(JoinPoint joinPoint) {

        System.out.println("Before any update method in ServiceImplementation");

        Object[] args = joinPoint.getArgs();

        if (args.length > 0 && (args[0] instanceof AdminRequestDto ||
                args[0] instanceof StudentRequestDto ||
                args[0] instanceof InstructorRequestDto)) {

            Object dto = args[0];

            if (dto instanceof InstructorRequestDto instructorRequestDto) {
                System.out.println("Instructor DTO detected");
                uniqueChecker.emailUniqueChecker(instructorRequestDto.getEmail());
                uniqueChecker.phoneNumberUniqueChecker(instructorRequestDto.getPhoneNumber());
            }
            if (dto instanceof StudentRequestDto studentRequestDto) {
                System.out.println("Student DTO detected");
                uniqueChecker.emailUniqueChecker(studentRequestDto.getEmail());
                uniqueChecker.phoneNumberUniqueChecker(studentRequestDto.getPhoneNumber());
            }
            if (dto instanceof AdminRequestDto adminRequestDto) {
                System.out.println("Admin DTO detected");
                uniqueChecker.emailUniqueChecker(adminRequestDto.getEmail());
                uniqueChecker.phoneNumberUniqueChecker(adminRequestDto.getPhoneNumber());
            }

        }

        System.out.println("Method: " + joinPoint.getSignature().getName());
        System.out.println("Arguments: " + Arrays.toString(args));
    }



//    @Before("execution(* com.demo.Attendance.serviceImplementation.*.update*(..))")
//    public void beforeUpdateService(JoinPoint joinPoint) {
//        System.out.println("Before any update method in ServiceImplementation");
//
//        Object[] args = joinPoint.getArgs();
//
//        if (args.length > 0) {
//            Object dto = args[0];
//
//            switch (dto) {
//                case AdminUpdateRequestDto adminUpdateRequestDto -> {
//                    System.out.println("Admin DTO detected");
//                    uniqueChecker.emailUniqueChecker(adminUpdateRequestDto.getEmail());
//                    uniqueChecker.phoneNumberUniqueChecker(adminUpdateRequestDto.getPhoneNumber());
//                }
//                case StudentUpdateRequestDto studentUpdateRequestDto -> {
//                    System.out.println("Student DTO detected");
//                    uniqueChecker.emailUniqueChecker(studentUpdateRequestDto.getEmail());
//                    uniqueChecker.phoneNumberUniqueChecker(studentUpdateRequestDto.getPhoneNumber());
//                }
//                case InstructorUpdateRequestDto instructorUpdateRequestDto -> {
//                    System.out.println("Instructor DTO detected");
//                    uniqueChecker.emailUniqueChecker(instructorUpdateRequestDto.getEmail());
//                    uniqueChecker.phoneNumberUniqueChecker(instructorUpdateRequestDto.getPhoneNumber());
//                }
//                default -> System.out.println("DTO type not recognized: " + dto.getClass().getName());
//            }
//        }
//
//        System.out.println("Method: " + joinPoint.getSignature().getName());
//        System.out.println("Arguments: " + Arrays.toString(args));
//    }



}
