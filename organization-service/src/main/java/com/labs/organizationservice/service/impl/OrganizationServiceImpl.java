package com.labs.organizationservice.service.impl;

import com.labs.organizationservice.dto.OrganizationDTO;
import com.labs.organizationservice.entity.Organization;
import com.labs.organizationservice.mapper.OrganizationMapper;
import com.labs.organizationservice.repository.OrganizationRepository;
import com.labs.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDTO saveOrganization(OrganizationDTO organizationDTO) {

        // Convert OrganizationDto to Organization jpa Entity
        Organization organization = OrganizationMapper.mapToOrganization(organizationDTO);

        Organization saveOrganization = organizationRepository.save(organization);

        return OrganizationMapper.mapToOrganizationDto(saveOrganization);
    }

    @Override
    public OrganizationDTO getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        return OrganizationMapper.mapToOrganizationDto(organization);
    }
}
