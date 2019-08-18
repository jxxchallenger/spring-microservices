package com.thoughtmechanix.licenses.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.thoughtmechanix.licenses.utils.UserContextHolder;

@RestController
@RequestMapping(value = {"/v1/organizations/{organizationId}/licenses"})
public class LicenseServiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LicenseServiceController.class);
    
    @Autowired
    private LicenseService licenseService;
    
    @GetMapping
    public List<License> getLicenses(@PathVariable(value = "organizationId") String organizationId) {
        LOGGER.info("LicenseServiceController Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        return this.licenseService.getLicensesByOrg(organizationId);
    }
    
    @GetMapping(value = {"/{licenseId}"})
    public License getLicenses(@PathVariable(value = "organizationId") String organizationId, @PathVariable(value = "licenseId") String licenseId) {

        return this.licenseService.getLicense(organizationId, licenseId);
    }
    
    @GetMapping(value = {"/{licenseId}/{clientType}"})
    public License getLicensesWithClient(@PathVariable(value = "organizationId") String organizationId, @PathVariable(value = "licenseId") String licenseId, @PathVariable(value = "clientType") String clientType) {
        return this.licenseService.getLicense(organizationId, licenseId, clientType);
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
