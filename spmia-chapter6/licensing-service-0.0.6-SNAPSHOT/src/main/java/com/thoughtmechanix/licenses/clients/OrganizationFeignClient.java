package com.thoughtmechanix.licenses.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.thoughtmechanix.licenses.model.Organization;

@FeignClient(value = "organizationservice")
public interface OrganizationFeignClient {

    @GetMapping(value = {"/v1/organizations/{organizationId}"}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    Organization getOrganization(@PathVariable(value = "organizationId") String organizationId);
}
