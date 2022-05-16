package com.co.indra.coinmarketcap.users.repositories;

import com.co.indra.coinmarketcap.users.model.MembershipType;
import com.co.indra.coinmarketcap.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getLong("userId"));
        user.setName(rs.getString("name"));
        user.setMail(rs.getString("mail"));
        return user;
    }
}
@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate template;

    public void createUser (User user) {
        Date date = new Date();

        template.update("INSERT INTO tbl_users(name, mail) values(?,?)",
            user.getMail(), new Timestamp(date.getTime()));
    }
    public List<User> findByLocationId(int id_user) {
        return template.query(
                "SELECT id_user, name, mail FROM tbl_users WHERE id_user=?",
                new UserRowMapper() ,
                id_user);
    }
    public List<User> findByUserMail(String name) {
        return template.query(
                "SELECT id_user, name, mail, updated_at FROM tbl_users WHERE mail=?",
                new UserRowMapper() ,
                name);
    }
}
