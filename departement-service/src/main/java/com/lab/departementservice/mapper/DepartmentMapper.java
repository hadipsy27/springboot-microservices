package com.lab.departementservice.mapper;

import com.lab.departementservice.dto.DepartmentDto;
import com.lab.departementservice.entity.Department;

public class DepartmentMapper {

    public static DepartmentDto mapToDepartmentDto(Department department){
        DepartmentDto departmentDto = new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentCode(),
                department.getDepartmentDescription()
        );
        return departmentDto;
    }

    public static Department mapToDeparment(DepartmentDto departmentDto){
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentCode(),
                departmentDto.getDepartmentDescription()
        );
        return department;
    }
}
