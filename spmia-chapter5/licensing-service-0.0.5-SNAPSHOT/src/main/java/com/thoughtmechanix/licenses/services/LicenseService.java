package com.thoughtmechanix.licenses.services;

import java.util.List;

import com.thoughtmechanix.licenses.model.License;

public interface LicenseService {

    License getLicense(String organizationId, String licenseId);
    
    License getLicense(String organizationId, String licenseId, String clientType);
    
    List<License> getLicensesByOrg(String organizationId);
    
    void saveLicense(License license);
    
    void updateLicense(License license);
    
    void deleteLicense(License license);
}
