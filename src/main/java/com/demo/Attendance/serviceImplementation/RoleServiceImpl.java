package com.demo.Attendance.serviceImplementation;

import com.demo.Attendance.dto.dtoRole.RoleRequestDto;
import com.demo.Attendance.dto.dtoRole.RoleResponseDto;
import com.demo.Attendance.exceptionHandling.NotFoundException;
import com.demo.Attendance.mapper.RoleMapper;
import com.demo.Attendance.model.Role;
import com.demo.Attendance.repository.RoleRepository;
import com.demo.Attendance.serviceInterface.RoleService;
import com.demo.Attendance.util.ConstantMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public List<RoleResponseDto> createRole(RoleRequestDto roleRequestDto) {

        List<RoleResponseDto> roleResponseDtos = new ArrayList<>();


        for (String roleName : roleRequestDto.getRoleName()) {
            Role role = new Role();
            role.setRoleName(roleName);
            roleRepository.save(role);

            RoleResponseDto roleResponseDto = RoleMapper.mapToRoleResponseDto(role);
            roleResponseDtos.add(roleResponseDto);
            System.out.println(roleResponseDtos.get(0));
        }
        return roleResponseDtos;
    }

    @Override
    public RoleResponseDto getRoleById(long id) {

        Role role = roleRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ConstantMessages.ROLE_WITH_ID+id + ConstantMessages.NOT_FOUND)
        );
        return RoleMapper.mapToRoleResponseDto(role);
    }

    @Override
    public List<RoleResponseDto> getAllRoles() {

        List<Role> roles = roleRepository.findAll();
        return RoleMapper.toRoleResponseDtoList(roles);
    }

    @Override
    public void deleteRoleById(long id) {

        Role role = roleRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ConstantMessages.ROLE_WITH_ID+id+ ConstantMessages.NOT_FOUND)
        );
        roleRepository.deleteById(id);
    }

}
