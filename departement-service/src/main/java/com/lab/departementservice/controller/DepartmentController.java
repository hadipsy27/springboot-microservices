package com.lab.departementservice.controller;

import com.lab.departementservice.dto.DepartmentDto;
import com.lab.departementservice.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Deparment service - DepartmentController",
        description = "Department controller exposes REST APIs for Department-service"
)
@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController {
    private DepartmentService departmentService;

    @Operation(
            summary = "Save Department REST API",
            description = "Save Department REST API is used to save department object in Database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status Created"
    )
    // Build Save Department Save REST API
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        final DepartmentDto saveDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(saveDepartment, HttpStatus.CREATED);
    }

    @Operation(
            summary = "GET Department REST API",
            description = "GET Department REST API is used to save department object in Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status SUKSES"
    )
    // Build Get Department By Code REST API
    @GetMapping("/{department-code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("department-code") String departmentCode) {
        final DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }
}
