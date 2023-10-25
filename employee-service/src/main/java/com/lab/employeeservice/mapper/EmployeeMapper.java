package com.lab.employeeservice.mapper;

import com.lab.employeeservice.dto.EmployeeDto;
import com.lab.employeeservice.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee){

        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getFirstName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );
        return employeeDto;
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){

        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );
        return employee;
    }
}
