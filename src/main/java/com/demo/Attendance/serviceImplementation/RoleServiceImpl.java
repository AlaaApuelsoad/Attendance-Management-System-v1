package com.demo.Attendance.serviceImplementation;

import com.demo.Attendance.dto.dtoRole.RoleRequestDto;
import com.demo.Attendance.dto.dtoRole.RoleResponseDto;
import com.demo.Attendance.exceptionHandling.NotFoundException;
import com.demo.Attendance.mapper.RoleMapper;
import com.demo.Attendance.model.Role;
import com.demo.Attendance.repository.RoleRepository;
import com.demo.Attendance.serviceInterface.RoleService;
import com.demo.Attendance.util.ConstantMessages;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final ObjectMapper objectMapper;


    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper, @Qualifier("objectMapper") ObjectMapper objectMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional
    public RoleResponseDto createRole(RoleRequestDto roleRequestDto) {

        Role role = roleMapper.mapToRole(roleRequestDto);
        roleRepository.save(role);
        return roleMapper.mapToDto(role);
    }

    @Override
    public RoleResponseDto getRoleById(long id) {

        Role role = roleRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ConstantMessages.ROLE_WITH_ID+id + ConstantMessages.NOT_FOUND)
        );
        return roleMapper.mapToDto(role);
    }

    @Override
    public List<RoleResponseDto> getAllRoles() {

        List<Role> roles = roleRepository.findAll();
        return roleMapper.mapToDtoList(roles);
    }

    @Override
    public void deleteRoleById(long id) {

        Role role = roleRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ConstantMessages.ROLE_WITH_ID+id+ ConstantMessages.NOT_FOUND)
        );
        roleRepository.deleteById(id);
    }

}
