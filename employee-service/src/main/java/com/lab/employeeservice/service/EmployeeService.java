package com.lab.employeeservice.service;

import com.lab.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    Object getEmployeeById(Long employeeId);
}
