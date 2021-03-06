package com.co.indra.coinmarketcap.users.repositories;

import com.co.indra.coinmarketcap.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class UserRowMapper implements RowMapper<User> {
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserId(rs.getInt("id_user"));
		user.setName(rs.getString("name"));
		user.setMail(rs.getString("mail"));
		user.setIdMembership(rs.getLong("membership_id"));
		return user;
	}
}

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate template;

    public void createUser (User user) {
        template.update("INSERT INTO tbl_users(name, mail, phone, membership_id) values(?,?,?,?)",
                user.getName(),  user.getMail(), user.getPhone(), user.getIdMembership());
    }

    public List<User> findByMail(String name) {
        return template.query(
                "SELECT id_user, name, mail, membership_id FROM tbl_users WHERE mail=?",
                new UserRowMapper() ,
                name);
    }

    public List<User> findUserById(int idUser) {
        return template.query(
                "SELECT id_user, name, mail, membership_id FROM tbl_users WHERE id_user=?",
                new UserRowMapper(),
                idUser);
    }

    public List<User> findUsers() {
        return template.query(
                "SELECT id_user, name, mail, membership_id FROM tbl_users",
                new UserRowMapper());
    }
}
