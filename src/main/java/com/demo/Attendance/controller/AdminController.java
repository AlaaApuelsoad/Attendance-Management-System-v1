package com.demo.Attendance.controller;

import com.demo.Attendance.dtoAdmin.AdminRequestDto;
import com.demo.Attendance.dtoAdmin.AdminResponseDto;
import com.demo.Attendance.dtoAdmin.AdminUpdateRequestDto;
import com.demo.Attendance.serviceInterface.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/admins")
    public ResponseEntity<AdminResponseDto> createAdmin(@Valid @RequestBody AdminRequestDto adminRequestDto){
        return ResponseEntity.ok(adminService.createAdmin(adminRequestDto));
    }

    @PatchMapping("/admins/{id}")
    public ResponseEntity<AdminResponseDto> updateAdmin(@PathVariable long id,
                                                      @Valid @RequestBody AdminUpdateRequestDto adminUpdateRequestDto){

        return ResponseEntity.ok(adminService.updateAdmin(adminUpdateRequestDto,id));
    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<String> deleteAdminById(@PathVariable long id){
        adminService.deleteAdmin(id);
        return ResponseEntity.ok("Admin with id- "+id+" Deleted successfully!");
    }

    @GetMapping("/admins/{id}")
    public ResponseEntity<AdminResponseDto> getAdminById(@PathVariable long id){
        return ResponseEntity.ok(adminService.getAdminById(id));
    }

//    @GetMapping("/admins")
//    public List<AdminResponseDto> getAllAdmins(){
//        return adminService.getAllAdmins();
//    }


}
