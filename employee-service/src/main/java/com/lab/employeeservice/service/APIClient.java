package com.lab.employeeservice.service;

import com.lab.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Implement Load Balancer
// Change url to Eureka Application name
@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {
    // Build Get Department By Code REST API
    @GetMapping("/api/departments/{department-code}")
    DepartmentDto getDepartmentByCode(@PathVariable("department-code") String departmentCode);
}
