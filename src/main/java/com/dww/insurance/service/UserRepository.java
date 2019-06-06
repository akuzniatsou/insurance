package com.dww.insurance.service;

import com.dww.insurance.domain.Credentials;
import com.dww.insurance.domain.QueryParam;
import com.dww.insurance.domain.SearchResult;
import com.dww.insurance.domain.UserRole;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserRepository {

    private Properties props = AppProperties.getInstance().getAppProps();

    public Credentials authorize(Credentials credentials) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(credentials.getPassword().getBytes());
            byte[] digest = md.digest();
            String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();

            conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("password"));
            stmt = conn.prepareStatement("select user_role from public.user where login = ? and pass = ?");
            stmt.setString(1, credentials.getLogin());
            stmt.setString(2, hash);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                credentials.setRole(UserRole.lookupById(rs.getInt(1)));
            }
            return credentials;
        } catch (SQLException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            closeConnection(conn, stmt);
        }
    }
    public List<Credentials> findUsers() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("password"));
            stmt = conn.prepareStatement("select * from public.user");
            ResultSet rs = stmt.executeQuery();
            List<Credentials> credentialsList = new ArrayList<>();
            while (rs.next()) {
                Credentials credentials = new Credentials(rs.getString(1), rs.getString(2));
                credentials.setRole(UserRole.lookupById(rs.getInt(3)));
                credentialsList.add(credentials);
            }
            return credentialsList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            closeConnection(conn, stmt);
        }
    }

    public List<Credentials> find(Credentials searchParam) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("password"));
            List<Object> parameters = new ArrayList<>();
            StringBuilder query = new StringBuilder("select * from public.user where 1=1");
            if (searchParam.getRole() != UserRole.ALL && searchParam.getRole() != null) {
                query.append(" and user_role = ?");
                parameters.add(searchParam.getRole().getId());
            }
            if (!isBlank(searchParam.getLogin())) {
                query.append(" and login ilike ?");
                parameters.add(searchParam.getLogin());
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
            List<Credentials> credentialsList = new ArrayList<>();
            while (rs.next()) {
                Credentials credentials = new Credentials(rs.getString(1), rs.getString(2));
                credentials.setRole(UserRole.lookupById(rs.getInt(3)));
                credentialsList.add(credentials);
            }
            return credentialsList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            closeConnection(conn, stmt);
        }
    }


    private void closeConnection(Connection conn, PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.length() == 0;
    }
}
