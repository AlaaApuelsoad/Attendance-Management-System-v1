package com.demo.Attendance.util;

import com.demo.Attendance.dto.dtoAdmin.AdminRequestDto;
import com.demo.Attendance.exceptionHandling.NotFoundException;
import com.demo.Attendance.model.Admin;
import com.demo.Attendance.model.Role;
import com.demo.Attendance.model.User;
import com.demo.Attendance.repository.AdminRepository;
import com.demo.Attendance.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUtil {

    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminUtil(AdminRepository adminRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Admin findAdminById(long id){
        return adminRepository.findById(id).orElseThrow(
                ()-> new NotFoundException(ConstantMessages.adminNotFound +id+ConstantMessages.notFoundMessage)
        );
    }

    public User setUserForAdmin(AdminRequestDto adminRequestDto) {

        Role adminRole = roleRepository.findByRoleName("ROLE_ADMIN");

        User adminUser = new User();
        adminUser.setRole(adminRole);
        adminUser.setUserName(adminRequestDto.getFirstName().toLowerCase().
                concat(adminRequestDto.getLastName().toLowerCase()));

        adminUser.setPassword(passwordEncoder.encode(adminRequestDto.getPassword()));

        return adminUser;
    }

    public void updateAdminDetails(Admin admin, AdminRequestDto adminUpdateRequestDto){

        User adminUser = admin.getUser();


        if (isValid(adminUpdateRequestDto.getPhoneNumber())){
            admin.setPhoneNumber(adminUpdateRequestDto.getPhoneNumber());
        }
        if (isValid(adminUpdateRequestDto.getEmail())){
            admin.setEmail(adminUpdateRequestDto.getEmail());
        }
        if (isValid(adminUpdateRequestDto.getPassword())){
            updateAdminPassword(admin,adminUpdateRequestDto.getPassword());
        }

        String firstNameUpdate = adminUpdateRequestDto.getFirstName();
        String lastNameUpdate = adminUpdateRequestDto.getLastName();

        if (isValid(firstNameUpdate)) {
            admin.setFirstName(firstNameUpdate);
        }
        if (isValid(lastNameUpdate)) {
            admin.setLastName(lastNameUpdate);
        }

        // Update userName based on new or existing first, last names
        String UserName = (firstNameUpdate != null ? firstNameUpdate : admin.getFirstName())
                + (lastNameUpdate != null ? lastNameUpdate : admin.getLastName());

        adminUser.setUserName(UserName.toLowerCase());

    }
    private boolean isValid(String value){
        return value !=null && !value.trim().isEmpty();
    }

    private void updateAdminPassword(Admin admin,String newPassword){
        User adminUser = admin.getUser();
        adminUser.setPassword(passwordEncoder.encode(newPassword));
        admin.setUser(adminUser);
    }

    public void updateUserNameWithId(long id,User user){
        String newUserName = user.getUserName()+id;
        user.setUserName(newUserName);
    }
}
