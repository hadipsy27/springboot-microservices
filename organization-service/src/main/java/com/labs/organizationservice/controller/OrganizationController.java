package com.labs.organizationservice.controller;

import com.labs.organizationservice.dto.OrganizationDTO;
import com.labs.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/organization")
public class OrganizationController {

    private OrganizationService organizationService;

    // Build save Organization Rest API
    @PostMapping
    public ResponseEntity<OrganizationDTO> saveOrganization(@RequestBody OrganizationDTO organizationDTO){
        OrganizationDTO saveOrganization = organizationService.saveOrganization(organizationDTO);
        return new ResponseEntity<>(saveOrganization, HttpStatus.CREATED);
    }


}
