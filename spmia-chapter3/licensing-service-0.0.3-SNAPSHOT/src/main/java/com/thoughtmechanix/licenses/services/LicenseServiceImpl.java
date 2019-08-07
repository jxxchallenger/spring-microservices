package com.thoughtmechanix.licenses.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtmechanix.licenses.model.License;
import com.thoughtmechanix.licenses.repository.LicenseRepository;

@Service
@Transactional
public class LicenseServiceImpl implements LicenseService {

    @Autowired
    private LicenseRepository licenseRepository;
    
    @Transactional(readOnly = true)
    @Override
    public License getLicense(String organizationId, String licenseId) {
        
        return this.licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<License> getLicensesByOrg(String organizationId) {
        
        return this.licenseRepository.findByOrganizationId(organizationId);
    }

    @Override
    public void saveLicense(License license) {
        license.setLicenseId(UUID.randomUUID().toString());
        this.licenseRepository.save(license);

    }

    @Override
    public void updateLicense(License license) {
        

    }

    @Override
    public void deleteLicense(License license) {
        

    }

}
