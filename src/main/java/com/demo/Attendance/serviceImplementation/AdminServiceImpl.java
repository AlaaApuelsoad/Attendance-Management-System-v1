package com.demo.Attendance.serviceImplementation;

import com.demo.Attendance.dtoAdmin.AdminRequestDto;
import com.demo.Attendance.dtoAdmin.AdminResponseDto;
import com.demo.Attendance.mapper.AdminMapper;
import com.demo.Attendance.model.Admin;
import com.demo.Attendance.model.User;
import com.demo.Attendance.repository.AdminRepository;
import com.demo.Attendance.repository.UserRepository;
import com.demo.Attendance.serviceInterface.AdminService;
import com.demo.Attendance.util.AdminUtil;
import com.demo.Attendance.util.UniqueChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final AdminUtil adminUtil;
    private final UniqueChecker uniqueChecker;
    private final AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, UserRepository userRepository,
                            AdminUtil adminUtil, UniqueChecker uniqueChecker, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.adminUtil = adminUtil;
        this.uniqueChecker = uniqueChecker;
        this.adminMapper = adminMapper;
    }


    @Override
    @Transactional
    public AdminResponseDto createAdmin(AdminRequestDto adminRequestDto) {

        //Unique checker for email and phoneNumber
        uniqueChecker.emailUniqueChecker(adminRequestDto.getEmail());
        uniqueChecker.phoneNumberUniqueChecker(adminRequestDto.getPhoneNumber());

        //Set user for admin
        User adminUser = adminUtil.setUserForAdmin(adminRequestDto);

        Admin admin = adminMapper.mapToAdmin(adminRequestDto);
        admin.setUser(adminUser);
        adminRepository.save(admin);
        adminUtil.updateUserNameWithId(admin.getId(), adminUser);

        userRepository.save(adminUser);

        System.out.println(admin);

        return adminMapper.mapToDto(admin);
    }

    @Override
    @Transactional
    public AdminResponseDto updateAdmin(AdminRequestDto adminRequestDto, long id) {

        // Fetch instructor and throw if not found
        Admin admin = adminUtil.findAdminById(id);

        //Unique checker for email and phoneNumber
        uniqueChecker.emailUniqueChecker(adminRequestDto.getEmail());
        uniqueChecker.phoneNumberUniqueChecker(adminRequestDto.getPhoneNumber());

        User adminUser = admin.getUser();

        // update admin details
        adminUtil.updateAdminDetails(admin, adminRequestDto);

        // update userName with id
        adminUtil.updateUserNameWithId(admin.getId(), adminUser);

        admin.setUser(adminUser);

        adminRepository.save(admin);

        return adminMapper.mapToDto(admin);
    }

    @Override
    @Transactional
    public void deleteAdmin(long id) {
        Admin admin = adminUtil.findAdminById(id);
        adminRepository.deleteById(id);
    }

    @Override
    public AdminResponseDto getAdminById(long id) {

        Admin admin = adminUtil.findAdminById(id);
        return adminMapper.mapToDto(admin);
    }

    @Override
    public List<AdminResponseDto> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return adminMapper.mapToDtolist(admins);
    }
}
