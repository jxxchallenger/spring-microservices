package com.thoughtmechanix.licenses.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thoughtmechanix.licenses.model.License;

@RestController
@RequestMapping(value = {"/v1/organizations/{organizationId}/licenses"})
public class LicenseServiceController {

    @GetMapping(value = {"/{licenseId}"})
    public License getLicenses(@PathVariable(value = "organizationId") String organizationId, @PathVariable(value = "licenseId") String licenseId) {

        return new License().withId(licenseId).withProductName("Teleco")
                .withLicenseType("Seat").withOrganizationId("TestOrg");
    }
}
