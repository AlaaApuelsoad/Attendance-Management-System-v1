package com.demo.Attendance.serviceImplementation;

import com.demo.Attendance.dto.dtoStudent.StudentRequestDto;
import com.demo.Attendance.dto.dtoStudent.StudentResponseDto;
import com.demo.Attendance.model.Student;
import com.demo.Attendance.mapper.StudentMapper;
import com.demo.Attendance.model.User;
import com.demo.Attendance.repository.StudentRepository;
import com.demo.Attendance.repository.UserRepository;
import com.demo.Attendance.serviceInterface.StudentService;
import com.demo.Attendance.util.StudentUtil;
import com.demo.Attendance.util.UniqueChecker;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;


@Service
public class StudentServiceImp implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final UniqueChecker uniqueChecker;
    private final StudentUtil studentUtil;

    private final StudentMapper studentMapper;

    @Autowired
    public StudentServiceImp(StudentRepository studentRepository, UserRepository userRepository, UniqueChecker uniqueChecker, StudentUtil studentUtil, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.uniqueChecker = uniqueChecker;
        this.studentUtil = studentUtil;
        this.studentMapper = studentMapper;
    }

//    @PostConstruct
    @Transactional
    public void init() {
        Faker faker = new Faker();

        List<StudentRequestDto> studentRequestDtoList = IntStream.range(0, 0)
                .mapToObj(i -> new StudentRequestDto(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        faker.phoneNumber().subscriberNumber(11),
                        faker.internet().password(6, 20)
                ))
                .toList();

        studentRequestDtoList.forEach(this::createStudent);
    }


    @Override
    @Transactional
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {

        uniqueChecker.emailUniqueChecker(studentRequestDto.getEmail());
        uniqueChecker.phoneNumberUniqueChecker(studentRequestDto.getPhoneNumber());

        User studentUser = studentUtil.setUserForStudent(studentRequestDto);

        Student student = studentMapper.mapToStudent(studentRequestDto);

        student.setUser(studentUser);

        studentRepository.save(student);

        studentUtil.updateUserNameWithId(student.getId(),studentUser);

        userRepository.save(studentUser);

        return studentMapper.mapToDto(student);
    }

    @Override
    @Transactional
    public StudentResponseDto updateStudent(long id, StudentRequestDto studentRequestDto) {

        Student student = studentUtil.findStudentById(id);

        uniqueChecker.emailUniqueChecker(studentRequestDto.getEmail());
        uniqueChecker.phoneNumberUniqueChecker(studentRequestDto.getPhoneNumber());

        studentUtil.updateStudentDetails(student,studentRequestDto);

        studentRepository.save(student);

        return studentMapper.mapToDto(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(long id) {

        Student student = studentUtil.findStudentById(id);
        studentRepository.deleteById(id);
    }

    @Override
    public StudentResponseDto getStudentById(long id) {

        Student student = studentUtil.findStudentById(id);
        return studentMapper.mapToDto(student);
    }


    @Override
    public Page<StudentResponseDto> getAllStudents(Pageable pageable) {

        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage.map(studentMapper::mapToDto);
    }

    @Override
    public Page<StudentResponseDto> searchStudentByName(String name, Pageable pageable) {

        Page<Student> studentPage = studentRepository.searchStudentByName(name,pageable);
        return studentPage.map(studentMapper::mapToDto);
    }

}
