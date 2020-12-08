package com.company.dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserDaoJDBCImpl implements UserDao {

    private Connection connection;

    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    private final RowMapper<User> userRowMapper = row -> new User(row.getInt("id"),
            row.getString("username"),
            row.getString("password"),
            row.getString("email"));


    public User get(int id) {
        String SQL = "SELECT * from public.user WHERE id = ?";
        try (PreparedStatement stat = connection.prepareStatement(SQL)) {
            stat.setInt(1, id); //!
            try (ResultSet rs = stat.executeQuery()) {
                if (rs.next()) {
                    return userRowMapper.mapRow(rs); //!
                }
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public void create(User item) {
        int i = 1;
        String SQL = "INSERT INTO public.user(username, password, email) " +
                "VALUES (?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setString(i++, item.getUsername());
            stmt.setString(i++, item.getPassword());
            stmt.setString(i++, item.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Object get(String email) {
        return null;
    }
}
