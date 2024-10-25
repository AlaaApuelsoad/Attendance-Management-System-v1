package com.demo.Attendance.controller;

import com.demo.Attendance.dto.dtoRole.RoleRequestDto;
import com.demo.Attendance.dto.dtoRole.RoleResponseDto;
import com.demo.Attendance.serviceInterface.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/roles")
    public ResponseEntity<RoleResponseDto> createRoles(@Valid @RequestBody RoleRequestDto roleRequestDto){
        return new ResponseEntity<>(roleService.createRole(roleRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleResponseDto>> getAllRoles(){
        return new ResponseEntity<>(roleService.getAllRoles(),HttpStatus.ACCEPTED);
    }
    @GetMapping("/roles/{id}")
    public ResponseEntity<RoleResponseDto> getRoleById(@PathVariable long id){
        return new ResponseEntity<>(roleService.getRoleById(id),HttpStatus.FOUND);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<String> deleteRoleById(@PathVariable long id){
        roleService.deleteRoleById(id);
        return new ResponseEntity<>("Role with id- "+id+" Deleted successfully!",HttpStatus.ACCEPTED);
    }

}
