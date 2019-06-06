package com.dww.insurance.service;

import com.dww.insurance.domain.Credentials;
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
                credentials.setRole(UserRole.lookupById(rs.getString(1)));
            }
            return credentials;
        } catch (SQLException | NoSuchAlgorithmException ex) {
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
}
