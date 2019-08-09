package com.thoughtmechanix.organization.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtmechanix.organization.model.Organization;
import com.thoughtmechanix.organization.repository.OrganizationRepository;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;
    
    @Transactional(readOnly = true)
    @Override
    public Organization getOrg(String organizationId) {
        
        return this.organizationRepository.findById(organizationId);
    }

    @Override
    public void saveOrg(Organization org) {
        org.setId(UUID.randomUUID().toString());
        this.organizationRepository.save(org);
    }

    @Override
    public void updateOrg(Organization org) {
        
        this.organizationRepository.update(org);
    }

    @Override
    public void deleteOrg(Organization org) {
        this.organizationRepository.delete(org);

    }

}
