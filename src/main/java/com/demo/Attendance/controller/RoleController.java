package com.demo.Attendance.controller;

import com.demo.Attendance.dtoRole.RoleRequestDto;
import com.demo.Attendance.dtoRole.RoleResponseDto;
import com.demo.Attendance.serviceInterface.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/roles")
    public List<RoleResponseDto> createRoles(@Valid @RequestBody RoleRequestDto roleRequestDto){
        return roleService.createRole(roleRequestDto);
    }

    @GetMapping("/roles")
    public List<RoleResponseDto> getAllRoles(){
        return roleService.getAllRoles();
    }
    @GetMapping("/roles/{id}")
    public ResponseEntity<RoleResponseDto> getRoleById(@PathVariable long id){
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<String> deleteRoleById(@PathVariable long id){
        roleService.deleteRoleById(id);
        return ResponseEntity.ok("Role with id- "+id+" Deleted successfully!");
    }

}
