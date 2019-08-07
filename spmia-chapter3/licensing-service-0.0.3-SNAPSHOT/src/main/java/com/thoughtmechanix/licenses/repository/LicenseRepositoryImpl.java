package com.thoughtmechanix.licenses.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.thoughtmechanix.licenses.model.License;

@Repository
public class LicenseRepositoryImpl implements LicenseRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Override
    public List<License> findByOrganizationId(String organizationId) {
        final String sql = "SELECT license_id, organization_id, product_name, license_type FROM licenses WHERE organization_id = :organizationId";
        return this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("organizationId", organizationId), new BeanPropertyRowMapper<License>(License.class));
    }

    @Override
    public License findByOrganizationIdAndLicenseId(String organizationId, String licenseId) {
        final String sql = "SELECT license_id, organization_id, product_name, license_type FROM licenses WHERE organization_id = :organizationId AND license_id = :licenseId";
        
        return this.namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("organizationId", organizationId).addValue("licenseId", licenseId), new BeanPropertyRowMapper<License>(License.class));
    }

    @Override
    public int save(License license) {
        final String sql = "INSERT INTO licenses (license_id, organization_id, product_name, license_type) VALUES(:licenseId, :organizationId, :productName, :licenseType)";
        return this.namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(license));
    }

}
