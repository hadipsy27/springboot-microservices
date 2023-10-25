package com.lab.employeeservice.service.impl;

import com.lab.employeeservice.dto.APIResponseDto;
import com.lab.employeeservice.dto.DepartmentDto;
import com.lab.employeeservice.dto.EmployeeDto;
import com.lab.employeeservice.entity.Employee;
import com.lab.employeeservice.mapper.EmployeeMapper;
import com.lab.employeeservice.repository.EmployeeRepository;
import com.lab.employeeservice.service.APIClient;
import com.lab.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    EmployeeRepository employeeRepository;
    /* private RestTemplate restTemplate; */
     private WebClient webClient;
    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee saveEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(saveEmployee);
        return savedEmployeeDto;
    }

//    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        LOGGER.info("Inside getEmployeeById() Method");

        Employee employee = employeeRepository.findById(employeeId).get();

/*
        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/ " + employee.getDepartmentCode(),
                DepartmentDto.class);

        DepartmentDto departmentDto = responseEntity.getBody();
*/
        if(employee.getDepartmentCode() != null){

            DepartmentDto departmentDto = webClient.get()
                    // Dhange Departements url service from API Gateway to handle service employee by id in API Gateway service
                    .uri("http://localhost:9191/api/departments/" + employee.getDepartmentCode())
                    .retrieve()
                    .bodyToMono(DepartmentDto.class)
                    .block();

//            DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());

            EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

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

    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {

        LOGGER.info("Inside getDefaultDepartment() Method");

        Employee employee = employeeRepository.findById(employeeId).get();

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("R&D001");
        departmentDto.setDepartmentDescription("Research & Development Deparment");

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }
}
