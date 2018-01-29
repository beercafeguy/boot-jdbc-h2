package com.github.beercafeguy.bootjdbch2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentJdbcRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    //jdbc:h2:mem:testdb is the DB ULR
    //http://localhost:8082/h2-console is the URL of console


    public User findById(long id) {
        return jdbcTemplate.queryForObject("select * from user where id=?", new Object[]{id},
                new BeanPropertyRowMapper<User>(User.class));
    }

    public List<User> findAll(){
        return jdbcTemplate.query("select * from user", new UserRowMapper());
    }

    public int deleteById(long id) {
        return jdbcTemplate.update("delete from user where id=?", new Object[] { id });
    }

    public int insert(User user) {
        return jdbcTemplate.update("insert into user (id, name, email) " + "values(?,  ?, ?)",
                new Object[] { user.getId(), user.getName(), user.getEmail()});
    }

    public int update(User user) {
        return jdbcTemplate.update("update user " + " set name = ?, email = ? " + " where id = ?",
                new Object[] { user.getName(), user.getEmail(), user.getId() });
    }

    class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User student = new User(rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("email"));
            return student;
        }

    }
}