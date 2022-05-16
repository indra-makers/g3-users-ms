package com.co.indra.coinmarketcap.users.repositories;

import com.co.indra.coinmarketcap.users.model.MembershipType;
import com.co.indra.coinmarketcap.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class MembershipRowMapper implements RowMapper<MembershipType> {
    @Override
    public MembershipType mapRow(ResultSet rs, int rowNum) throws SQLException {
        MembershipType membershipType = new MembershipType();
        membershipType.setMembership_id(rs.getLong("membership_id"));
        membershipType.setType(rs.getString("type"));
        return membershipType;
    }
}
@Repository
public class MembershipTypeRepository {
    @Autowired
    private JdbcTemplate template;

    public List<MembershipType> findAll() {
        return template.query(
                "SELECT membership_id, type  FROM tbl_membership_types",
                new MembershipRowMapper());
    }
}

