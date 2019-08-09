package com.thoughtmechanix.licenses.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.thoughtmechanix.licenses.model.Organization;

@Component
public class OrganizationDiscoveryClient {

    @Autowired
    private DiscoveryClient discoveryClient;
    
    public Organization getOrganization(String organizationId) {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("organizationservice");
        
        if(instances == null) {
            return null;
        }
        
        //String serviceUri = String.format("%s/v1/organizations/%s", instances.get(0).getUri().toString(), organizationId);
        UriComponents components = UriComponentsBuilder.fromUri(instances.get(0).getUri()).path("/v1/organizations/{organizationId}").build().expand(organizationId).encode();
        ResponseEntity<Organization> responseEntity = restTemplate.getForEntity(components.toUri(), Organization.class);
        return responseEntity.getBody();
    }
}
