package com.thoughtmechanix.organization.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.thoughtmechanix.organization.model.Organization;
import com.thoughtmechanix.organization.services.OrganizationService;

@RestController
@RequestMapping(value = {"/v1/organizations"})
public class OrganizationServiceController {

    @Autowired
    private OrganizationService organizationService;
    
    @GetMapping(value = {"/{organizationId}"})
    public Organization getOrganization(@PathVariable("organizationId") String organizationId) {
        return this.organizationService.getOrg(organizationId);
    }

    @PutMapping(value = {"/{organizationId}"})
    public void updateOrganization(@PathVariable("organizationId") String orgId, @RequestBody Organization org) {
        org.setId(orgId);
        this.organizationService.updateOrg( org );
    }

    @PostMapping
    public void saveOrganization(@RequestBody Organization org) {
        this.organizationService.saveOrg( org );
    }

    @DeleteMapping(value = {"/{organizationId}"})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable("orgId") String orgId,  @RequestBody Organization org) {
        org.setId(orgId);
        this.organizationService.deleteOrg( org );
    }
}
