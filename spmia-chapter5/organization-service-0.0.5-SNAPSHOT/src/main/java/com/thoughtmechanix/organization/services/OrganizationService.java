package com.thoughtmechanix.organization.services;

import com.thoughtmechanix.organization.model.Organization;

public interface OrganizationService {

    Organization getOrg(String organizationId);
    
    void saveOrg(Organization org);
    
    void updateOrg(Organization org);
    
    void deleteOrg(Organization org);
}
