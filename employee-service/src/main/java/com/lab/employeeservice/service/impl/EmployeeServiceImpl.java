package com.lab.employeeservice.service.impl;

import com.lab.employeeservice.dto.APIResponseDto;
import com.lab.employeeservice.dto.DepartmentDto;
import com.lab.employeeservice.dto.EmployeeDto;
import com.lab.employeeservice.entity.Employee;
import com.lab.employeeservice.repository.EmployeeRepository;
import com.lab.employeeservice.service.APIClient;
import com.lab.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;
    /* private RestTemplate restTemplate; */
    /* private WebClient webClient; */
    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );

        Employee saveEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = new EmployeeDto(
                saveEmployee.getId(),
                saveEmployee.getFirstName(),
                saveEmployee.getLastName(),
                saveEmployee.getEmail(),
                saveEmployee.getDepartmentCode()
        );

        return savedEmployeeDto;
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();

/*
        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/ " + employee.getDepartmentCode(),
                DepartmentDto.class);

        DepartmentDto departmentDto = responseEntity.getBody();
*/
        if(employee.getDepartmentCode() != null){
/*
            DepartmentDto departmentDto = webClient.get()
                    .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                    .retrieve()
                    .bodyToMono(DepartmentDto.class)
                    .block();
*/
            DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());

            EmployeeDto employeeDto = new EmployeeDto(
                    employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmail(),
                    employee.getDepartmentCode()
            );

            APIResponseDto apiResponseDto = new APIResponseDto();
            apiResponseDto.setEmployee(employeeDto);
            apiResponseDto.setDepartment(departmentDto);

            return apiResponseDto;
        } else {
            APIResponseDto apiResponseDto = new APIResponseDto();
            apiResponseDto.setEmployee(EmployeeDto.builder()
                            .id(employee.getId())
                            .firstName(employee.getFirstName())
                            .lastName(employee.getLastName())
                            .email(employee.getEmail())
                    .build());

            return apiResponseDto;
        }

    }
}
