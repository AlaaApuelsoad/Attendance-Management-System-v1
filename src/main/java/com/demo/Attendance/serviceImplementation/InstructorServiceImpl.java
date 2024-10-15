package com.demo.Attendance.serviceImplementation;

import com.demo.Attendance.dto.dtoInstructor.InstructorRequestDto;
import com.demo.Attendance.dto.dtoInstructor.InstructorResponseDto;
import com.demo.Attendance.mapper.InstructorMapper;
import com.demo.Attendance.model.Instructor;
import com.demo.Attendance.model.User;
import com.demo.Attendance.repository.InstructorRepository;
import com.demo.Attendance.repository.UserRepository;
import com.demo.Attendance.serviceInterface.InstructorService;
import com.demo.Attendance.util.InstructorUtil;
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
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final UserRepository userRepository;
    private final UniqueChecker uniqueChecker;
    private final InstructorUtil instructorUtil;
    private final InstructorMapper instructorMapper;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository, UserRepository userRepository,
                                 UniqueChecker uniqueChecker, InstructorUtil instructorUtil, InstructorMapper instructorMapper) {
        this.instructorRepository = instructorRepository;
        this.userRepository = userRepository;
        this.uniqueChecker = uniqueChecker;
        this.instructorUtil = instructorUtil;
        this.instructorMapper = instructorMapper;
    }


    @Transactional
//    @PostConstruct
    public void init() {
        Faker faker = new Faker();

        List<InstructorRequestDto> instructorRequestDtoList = IntStream.range(0, 0)
                .mapToObj(i -> new InstructorRequestDto(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        faker.phoneNumber().subscriberNumber(11),
                        faker.internet().password(6, 20)
                ))
                .toList();

        instructorRequestDtoList.forEach(this::createInstructor);
    }

    @Override
    @Transactional
    public InstructorResponseDto createInstructor(InstructorRequestDto instructorRequestDto) {

        uniqueChecker.emailUniqueChecker(instructorRequestDto.getEmail());
        uniqueChecker.phoneNumberUniqueChecker(instructorRequestDto.getPhoneNumber());

        User instructorUser = instructorUtil.setUserForInstructor(instructorRequestDto);

        Instructor instructor = instructorMapper.mapToInstructor(instructorRequestDto);
        instructor.setUser(instructorUser);

        instructorRepository.save(instructor);
        instructorUtil.updateUserNameWithId(instructor.getId(), instructorUser);
        userRepository.save(instructorUser);

        return instructorMapper.mapToDto(instructor);
    }

    @Override
    @Transactional
    public InstructorResponseDto updateInstructor(long id, InstructorRequestDto instructorRequestDto) {

        Instructor instructor = instructorUtil.findInstructorById(id);

        uniqueChecker.emailUniqueChecker(instructorRequestDto.getEmail());
        uniqueChecker.phoneNumberUniqueChecker(instructorRequestDto.getPhoneNumber());

        instructorUtil.updateInstructorDetails(instructor, instructorRequestDto);

        instructorRepository.save(instructor);

        return instructorMapper.mapToDto(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructor(long id) {

        Instructor instructor = instructorUtil.findInstructorById(id);
        instructorRepository.deleteById(id);
    }

    @Override
    public InstructorResponseDto getInstructorById(long id) {

        Instructor instructor = instructorUtil.findInstructorById(id);
        return instructorMapper.mapToDto(instructor);
    }

    @Override
    public Page<InstructorResponseDto> getAllInstructor(Pageable pageable) {

        Page<Instructor> instructorPage = instructorRepository.findAll(pageable);
        return instructorPage.map(instructorMapper::mapToDto);
    }

    @Override
    public Page<InstructorResponseDto> searchByInstructorName(String name, Pageable pageable) {

        Page<Instructor> instructorPage = instructorRepository.searchByInstructorName(name, pageable);
        return instructorPage.map(instructorMapper::mapToDto);
    }

}
