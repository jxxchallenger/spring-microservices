package com.thoughtmechanix.organization.repository;

import com.thoughtmechanix.organization.model.Organization;

public interface OrganizationRepository {

    Organization findById(String organizationId);
    
    int save(Organization organization);
    
    int update(Organization organization);
    
    int delete(Organization organization);
}
