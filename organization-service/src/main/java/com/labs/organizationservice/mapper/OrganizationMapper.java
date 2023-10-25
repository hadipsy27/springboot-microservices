package com.labs.organizationservice.mapper;

import com.labs.organizationservice.dto.OrganizationDTO;
import com.labs.organizationservice.entity.Organization;

public class OrganizationMapper {

    public static OrganizationDTO mapToOrganizationDto(Organization organization){
        OrganizationDTO organizationDTO = new OrganizationDTO(
                organization.getId(),
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationCode(),
                organization.getCreatedDate()
        );
        return organizationDTO;
    }

    public static Organization mapToOrganization(OrganizationDTO organizationDTO){
        Organization organization = new Organization(
                organizationDTO.getId(),
                organizationDTO.getOrganizationName(),
                organizationDTO.getOrganizationDescription(),
                organizationDTO.getOrganizationCode(),
                organizationDTO.getCreatedDate()
        );
        return organization;
    }
}
