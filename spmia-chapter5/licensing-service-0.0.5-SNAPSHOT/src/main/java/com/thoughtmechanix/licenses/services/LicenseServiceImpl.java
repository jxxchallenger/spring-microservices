package com.thoughtmechanix.licenses.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import com.thoughtmechanix.licenses.clients.OrganizationDiscoveryClient;
import com.thoughtmechanix.licenses.clients.OrganizationFeignClient;
import com.thoughtmechanix.licenses.clients.OrganizationRestTemplateClient;
import com.thoughtmechanix.licenses.model.License;
import com.thoughtmechanix.licenses.model.Organization;
import com.thoughtmechanix.licenses.repository.LicenseRepository;

@Service
@Transactional
public class LicenseServiceImpl implements LicenseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LicenseServiceImpl.class);
    
    @Autowired
    private LicenseRepository licenseRepository;
    
    @Autowired
    private OrganizationDiscoveryClient organizationDiscoveryClient;
    
    @Autowired
    private OrganizationRestTemplateClient organizationRestTemplateClient;
    
    @Autowired
    private OrganizationFeignClient organizationFeignClient;
    
    @Transactional(readOnly = true)
    @Override
    public License getLicense(String organizationId, String licenseId) {
        
        return this.licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
    }

    //@HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "15000")})
    //@HystrixCommand(commandProperties = {@HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "15000")}, fallbackMethod = "buildFallbackLicenseList")
    @HystrixCommand(commandProperties = {@HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "5000")}, fallbackMethod = "buildFallbackLicenseList")
    @Transactional(readOnly = true)
    @Override
    public List<License> getLicensesByOrg(String organizationId) {
        randomlyRunLong();
        return this.licenseRepository.findByOrganizationId(organizationId);
    }
    
    private void randomlyRunLong() {
        Random random = new Random();
        int randomNum = random.nextInt((3 - 1) + 1) + 1;
        if(randomNum == 3) {
            sleep();
            LOGGER.info("spend 11000 milliseconds");
        }
    }
    
    private void sleep() {
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            
            e.printStackTrace();
        }
    }
    
    protected List<License> buildFallbackLicenseList(String organizationId) {
        List<License> fallbackList = new ArrayList<License>();
        License license = new License().withId("0000000-00-00000")
                                       .withOrganizationId(organizationId)
                                       .withProductName("Sorry no licensing information currently available");
        
        fallbackList.add(license);
        return fallbackList;
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

    @HystrixCommand
    @Transactional(readOnly = true)
    @Override
    public License getLicense(String organizationId, String licenseId,
            String clientType) {
        License license = this.licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        Organization organization = retrieveOrgInfo(organizationId, clientType);
        return license.withOrganizationName(organization.getName())
                .withContactName(organization.getContactName())
                .withContactEmail(organization.getContactEmail())
                .withContactPhone(organization.getContactPhone());
    }

    private Organization retrieveOrgInfo(String organizationId, String clientType) {
        Organization organization;
        
        switch (clientType) {
            case "feign" :
                LOGGER.info("I am using the feign client");
                organization = this.organizationFeignClient.getOrganization(organizationId);
                break;
            case "rest" :
                LOGGER.info("I am using the rest client");
                organization = this.organizationRestTemplateClient.getOrganization(organizationId);
                break;
                
            case "discovery" :
                LOGGER.info("I am using the discovery client");
            default :
                organization = this.organizationDiscoveryClient.getOrganization(organizationId);
                break;
        }
        
        return organization;
    }
}
