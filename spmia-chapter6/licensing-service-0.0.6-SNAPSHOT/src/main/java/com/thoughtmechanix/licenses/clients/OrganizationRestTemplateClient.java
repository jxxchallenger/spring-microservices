package com.thoughtmechanix.licenses.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.thoughtmechanix.licenses.model.Organization;

@Component
public class OrganizationRestTemplateClient {

    @Autowired
    private RestTemplate restTemplate;
    
    public Organization getOrganization(String organizationId) {
        ResponseEntity<Organization> responseEntity = this.restTemplate.getForEntity("http://organizationservice/v1/organizations/{organizationId}", Organization.class, organizationId);
        return responseEntity.getBody();
    }
}
