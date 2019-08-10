package com.dww.insurance.repository;

import com.dww.insurance.domain.*;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static final String SELECT_STATEMENT = "select user_role from public.user where login = ? and pass = ?";
    private static final String SELECT_ALL_STATEMENT = "select * from public.user";
    private static final String DELETE_STATEMENT = "delete from public.user where login = ?";
    private static final String INSERT_STATEMENT = "insert into public.user (login, pass, user_role) values (?,?,?)";

    public User authorize(User user) {
        Connection conn = null;
        PreparedStatement stmt;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(user.getPassword().getBytes());
            byte[] digest = md.digest();
            String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();

            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(SELECT_STATEMENT);
            stmt.setString(1, user.getLogin());
            stmt.setString(2, hash);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user.setRole(UserRole.lookupById(rs.getInt(1)));
            }
            return user;
        } catch (SQLException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            ConnectionPool.getInstance().releaseConnection(conn);
        }
    }

    public List<User> findUsers() {
        Connection conn = null;
        PreparedStatement stmt;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(SELECT_ALL_STATEMENT);
            ResultSet rs = stmt.executeQuery();
            List<User> credentialsList = new ArrayList<>();
            while (rs.next()) {
                User credentials = new User(rs.getString(1), rs.getString(2));
                credentials.setRole(UserRole.lookupById(rs.getInt(3)));
                credentialsList.add(credentials);
            }
            return credentialsList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            ConnectionPool.getInstance().releaseConnection(conn);
        }
    }

    public List<User> find(User user) {
        Connection conn = null;
        PreparedStatement stmt;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            List<Object> parameters = new ArrayList<>();
            StringBuilder query = new StringBuilder("select * from public.user where 1=1");
            if (user.getRole() != UserRole.ALL && user.getRole() != null) {
                query.append(" and user_role = ?");
                parameters.add(user.getRole().getId());
            }
            if (!isBlank(user.getLogin())) {
                query.append(" and login ilike ?");
                parameters.add(user.getLogin());
            }
            stmt = conn.prepareStatement(query.toString());
            for (int i = 0; i < parameters.size(); i++) {
                Object parameter = parameters.get(i);
                if (parameter instanceof String) {
                    stmt.setObject(i+1, "%" + parameter + "%");
                } else {
                    stmt.setObject(i+1, parameter);
                }
            }
            ResultSet rs = stmt.executeQuery();
            List<User> credentialsList = new ArrayList<>();
            while (rs.next()) {
                User credentials = new User(rs.getString(1), rs.getString(2));
                credentials.setRole(UserRole.lookupById(rs.getInt(3)));
                credentialsList.add(credentials);
            }
            return credentialsList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            ConnectionPool.getInstance().releaseConnection(conn);
        }
    }

    public void deleteUser(String login) {
        Connection conn = null;
        PreparedStatement stmt;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(DELETE_STATEMENT);
            stmt.setString(1, login);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            ConnectionPool.getInstance().releaseConnection(conn);
        }
    }

    public void insertUser(User user) {
        Connection conn = null;
        PreparedStatement stmt;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(user.getPassword().getBytes());
            byte[] digest = md.digest();
            String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            stmt = conn.prepareStatement(INSERT_STATEMENT);
            stmt.setString(1, user.getLogin());
            stmt.setString(2, hash);
            stmt.setInt(3, user.getRole().getId());
            stmt.executeUpdate();
        } catch (SQLException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            ConnectionPool.getInstance().releaseConnection(conn);
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.length() == 0;
    }
}
