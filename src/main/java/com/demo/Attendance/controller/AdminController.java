package com.demo.Attendance.controller;

import com.demo.Attendance.dto.dtoAdmin.AdminRequestDto;
import com.demo.Attendance.dto.dtoAdmin.AdminResponseDto;
import com.demo.Attendance.serviceInterface.AdminService;
import com.demo.Attendance.serviceInterface.OnCreate;
import com.demo.Attendance.serviceInterface.OnUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register/admins")
    public ResponseEntity<AdminResponseDto> createAdmin(@Validated(OnCreate.class) @RequestBody AdminRequestDto adminRequestDto) {
        System.out.println("in controller");
        return new ResponseEntity<>(adminService.createAdmin(adminRequestDto), HttpStatus.CREATED);
    }

    @PatchMapping("/admins/{id}")
    public ResponseEntity<AdminResponseDto> updateAdmin(@PathVariable long id,
                                                        @Validated(OnUpdate.class) @RequestBody AdminRequestDto adminRequestDto) {

        return new ResponseEntity<>(adminService.updateAdmin(adminRequestDto, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<String> deleteAdminById(@PathVariable long id) {
        adminService.deleteAdmin(id);
        return new ResponseEntity<>("Admin with id- " + id + " Deleted successfully!", HttpStatus.ACCEPTED);
    }

    @GetMapping("/admins/{id}")
    public ResponseEntity<AdminResponseDto> getAdminById(@PathVariable long id) {

        return new ResponseEntity<>(adminService.getAdminById(id), HttpStatus.FOUND);
    }

    @GetMapping("/admins")
    public ResponseEntity<Page<AdminResponseDto>> getAllAdmins(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(adminService.getAllAdmins(pageable), HttpStatus.ACCEPTED);
    }

    @GetMapping("/admins/search")
    public ResponseEntity<Page<AdminResponseDto>> searchAdminByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return new ResponseEntity<>(adminService.searchAdminByName(name, pageable), HttpStatus.ACCEPTED);
    }

}
