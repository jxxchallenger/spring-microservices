package com.thoughtmechanix.licenses.repository;

import java.util.List;

import com.thoughtmechanix.licenses.model.License;

public interface LicenseRepository {

    List<License> findByOrganizationId(String organizationId);
    
    License findByOrganizationIdAndLicenseId(String organizationId, String licenseId);
    
    int save(License license);
}
