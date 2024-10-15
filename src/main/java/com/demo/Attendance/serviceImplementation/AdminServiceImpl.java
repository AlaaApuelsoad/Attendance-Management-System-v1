package com.demo.Attendance.serviceImplementation;

import com.demo.Attendance.dto.dtoAdmin.AdminRequestDto;
import com.demo.Attendance.dto.dtoAdmin.AdminResponseDto;
import com.demo.Attendance.mapper.AdminMapper;
import com.demo.Attendance.mapper.AdminMapping;
import com.demo.Attendance.model.Admin;
import com.demo.Attendance.model.User;
import com.demo.Attendance.repository.AdminRepository;
import com.demo.Attendance.repository.UserRepository;
import com.demo.Attendance.serviceInterface.AdminService;
import com.demo.Attendance.util.AdminUtil;
import com.demo.Attendance.util.UniqueChecker;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final AdminUtil adminUtil;
    private final UniqueChecker uniqueChecker;
    private final AdminMapper adminMapper;
    private final AdminMapping adminMapping;


    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, UserRepository userRepository,
                            AdminUtil adminUtil, UniqueChecker uniqueChecker, AdminMapper adminMapper, AdminMapping adminMapping) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.adminUtil = adminUtil;
        this.uniqueChecker = uniqueChecker;
        this.adminMapper = adminMapper;
        this.adminMapping = adminMapping;
    }

//    @PostConstruct
//    @Scheduled(fixedDelay = 30000)
    @Transactional
    public void init() {

        Faker faker = new Faker();

        List<AdminRequestDto> adminRequestDtoList = IntStream.range(0, 0)
                .mapToObj(i -> new AdminRequestDto(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        faker.phoneNumber().subscriberNumber(11),
                        faker.internet().password(6, 20)
                ))
                .toList();
        adminRequestDtoList.forEach(this::createAdmin);
    }

    @Override
    @Transactional
    public AdminResponseDto createAdmin(AdminRequestDto adminRequestDto) {

        uniqueChecker.emailUniqueChecker(adminRequestDto.getEmail());
        uniqueChecker.phoneNumberUniqueChecker(adminRequestDto.getPhoneNumber());

        User adminUser = adminUtil.setUserForAdmin(adminRequestDto);

        Admin admin = adminMapping.mapToAdmin(adminRequestDto);
        admin.setUser(adminUser);
        adminRepository.save(admin);
        adminUtil.updateUserNameWithId(admin.getId(), adminUser);

        userRepository.save(adminUser);

        return adminMapper.mapToDto(admin);
    }

    @Override
    @Transactional
    public AdminResponseDto updateAdmin(AdminRequestDto adminRequestDto, long id) {

        Admin admin = adminUtil.findAdminById(id);

        uniqueChecker.emailUniqueChecker(adminRequestDto.getEmail());
        uniqueChecker.phoneNumberUniqueChecker(adminRequestDto.getPhoneNumber());

        User adminUser = admin.getUser();

        adminUtil.updateAdminDetails(admin, adminRequestDto);

        adminUtil.updateUserNameWithId(admin.getId(), adminUser);

        admin.setUser(adminUser);

        adminRepository.save(admin);

        return adminMapper.mapToDto(admin);
    }

    @Override
    @Transactional
    public void deleteAdmin(long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public AdminResponseDto getAdminById(long id) {

        Admin admin = adminUtil.findAdminById(id);
        return adminMapper.mapToDto(admin);
    }

    @Override
    public Page<AdminResponseDto> getAllAdmins(Pageable pageable) {
        Page<Admin> adminPage = adminRepository.findAll(pageable);
        return adminPage.map(adminMapper::mapToDto);
    }

    @Override
    public Page<AdminResponseDto> searchAdminByName(String name, Pageable pageable) {

        Page<Admin> adminPage = adminRepository.searchAdminByName(name, pageable);
        return adminPage.map(adminMapper::mapToDto);
    }

}
