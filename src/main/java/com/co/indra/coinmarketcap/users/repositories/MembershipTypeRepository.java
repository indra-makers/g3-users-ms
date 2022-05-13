package com.co.indra.coinmarketcap.users.repositories;

import com.co.indra.coinmarketcap.users.model.MembershipType;
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
        membershipType.setIdMembership(rs.getLong("idMembership"));
        membershipType.setType(rs.getString("type"));
        return membershipType;
    }
}
@Repository
public class MembershipTypeRepository {
    @Autowired
    private JdbcTemplate template;
}
