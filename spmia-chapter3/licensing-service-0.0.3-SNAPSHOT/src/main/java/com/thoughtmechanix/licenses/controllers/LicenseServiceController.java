package com.thoughtmechanix.licenses.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thoughtmechanix.licenses.model.License;
import com.thoughtmechanix.licenses.services.LicenseService;

@RestController
@RequestMapping(value = {"/v1/organizations/{organizationId}/licenses"})
public class LicenseServiceController {

    @Autowired
    private LicenseService licenseService;
    
    @GetMapping
    public List<License> getLicenses(@PathVariable(value = "organizationId") String organizationId) {
        return this.licenseService.getLicensesByOrg(organizationId);
    }
    
    @GetMapping(value = {"/{licenseId}"})
    public License getLicenses(@PathVariable(value = "organizationId") String organizationId, @PathVariable(value = "licenseId") String licenseId) {

        return this.licenseService.getLicense(organizationId, licenseId);
    }
    
    @PostMapping
    public void saveLicense(@RequestBody License license) {
        this.licenseService.saveLicense(license);
    }
    
    @PutMapping(value = {"/{licenseId}"})
    public String updateLicenses(@PathVariable("licenseId") String licenseId) {
        return String.format("This is the put");
    }
    
    @DeleteMapping(value = {"/{licenseId}"})
    public String deleteLicenses(@PathVariable("licenseId") String licenseId) {
        return String.format("This is the Delete");
    }
    
}
