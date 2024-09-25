package com.demo.Attendance.serviceImplementation;

import com.demo.Attendance.dtoStudent.StudentRequestDto;
import com.demo.Attendance.dtoStudent.StudentResponseDto;
import com.demo.Attendance.model.Student;
import com.demo.Attendance.mapper.StudentMapper;
import com.demo.Attendance.model.User;
import com.demo.Attendance.repository.StudentRepository;
import com.demo.Attendance.repository.UserRepository;
import com.demo.Attendance.serviceInterface.StudentService;
import com.demo.Attendance.util.StudentUtil;
import com.demo.Attendance.util.UniqueChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class StudentServiceImp implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final UniqueChecker uniqueChecker;
    private final StudentUtil studentUtil;

    @Autowired
    public StudentServiceImp(StudentRepository studentRepository, UserRepository userRepository, UniqueChecker uniqueChecker, StudentUtil studentUtil) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.uniqueChecker = uniqueChecker;
        this.studentUtil = studentUtil;
    }


    @Override
    @Transactional
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {

        uniqueChecker.emailUniqueChecker(studentRequestDto.getEmail());
        uniqueChecker.phoneNumberUniqueChecker(studentRequestDto.getPhoneNumber());

        User studentUser = studentUtil.setUserForStudent(studentRequestDto);

        Student student = StudentMapper.mapToStudent(studentRequestDto);

        student.setUser(studentUser);

        studentRepository.save(student);

        studentUtil.updateUserNameWithId(student.getId(),studentUser);

        userRepository.save(studentUser);

        return StudentMapper.mapToStudentResponseDto(student);
    }

    @Override
    @Transactional
    public StudentResponseDto updateStudent(long id, StudentRequestDto studentRequestDto) {

        Student student = studentUtil.findStudentById(id);

        uniqueChecker.emailUniqueChecker(studentRequestDto.getEmail());
        uniqueChecker.phoneNumberUniqueChecker(studentRequestDto.getPhoneNumber());

        studentUtil.updateStudentDetails(student,studentRequestDto);

        studentRepository.save(student);

        return StudentMapper.mapToStudentResponseDto(student);
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
        return StudentMapper.mapToStudentResponseDto(student);
    }


    @Override
    public List<StudentResponseDto> getAllStudents() {

        List<Student> studentList = studentRepository.findAll();
        return StudentMapper.toStudentResponseDTOList(studentList);
    }

}
