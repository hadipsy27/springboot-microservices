package com.lab.departementservice.service.impl;

import com.lab.departementservice.dto.DepartmentDto;
import com.lab.departementservice.entity.Department;
import com.lab.departementservice.mapper.DepartmentMapper;
import com.lab.departementservice.repository.DepartmentRepository;
import com.lab.departementservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        // Convert Department DTO to Department JPA Entity
        Department department = DepartmentMapper.mapToDeparment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        DepartmentDto savedDepartmentDto = DepartmentMapper.mapToDepartmentDto(savedDepartment);

        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        DepartmentDto departmentDto = DepartmentMapper.mapToDepartmentDto(department);
        return departmentDto;
    }
}
