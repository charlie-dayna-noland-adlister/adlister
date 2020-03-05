package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUsersDao implements Users {

    private Connection connection = null;

    public MySQLUsersDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getDBUsername(),
                    config.getDBPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }
    public List<User> all() {
        PreparedStatement stmt = null;
        try {
            String selectQuery = "SELECT * FROM ?";
            stmt = connection.prepareStatement(selectQuery);
            stmt.setString(1, "users");
            ResultSet rs = stmt.executeQuery();
            return createUsersFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }
    @Override
    public Long insert(User user) {
        try {
            String updateStmt = "INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?);";
            PreparedStatement stmt = connection.prepareStatement(updateStmt, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, user.getId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    private User extractUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password")
        );
    }

    private List<User> createUsersFromResults(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            users.add(extractUser(rs));
        }
        return users;
    }
    @Override
    public User findByUsername(String username) {
        PreparedStatement stmt = null;
        try {
            String selectQuery = "SELECT * FROM users WHERE username = ?";
            stmt = connection.prepareStatement(selectQuery);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()) {
              return null;
            }
            rs.next();
            return extractUser(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error searching username.", e);
        }
    }
}
