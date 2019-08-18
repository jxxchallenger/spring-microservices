package com.thoughtmechanix.organization.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.thoughtmechanix.organization.model.Organization;

@Repository
public class OrganizationRepositoryImpl implements OrganizationRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Override
    public Organization findById(String organizationId) {
        final String sql = "SELECT id, name, contact_name, contact_email, contact_phone FROM organizations WHERE id = :organizationId";
        return this.namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("organizationId", organizationId), new BeanPropertyRowMapper<Organization>(Organization.class));
    }

    @Override
    public int save(Organization organization) {
        final String sql = "INSERT INTO organizations (id, name, contact_name, contact_email, contact_phone) VALUES(:id, :name, :contactName, :contactEmail, :contactPhone)";
        return this.namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(organization));
    }

    @Override
    public int update(Organization organization) {
        final String sql = "UPDATE organizations SET name = :name, contact_name = :contactName, contact_email = :contactEmail, contact_phone = :contactPhone WHERE id = :id";
        return this.namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(organization));
    }

    @Override
    public int delete(Organization organization) {
        final String sql = "DELETE FROM organizations WHERE id = :id";
        return this.namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(organization));
    }

}
