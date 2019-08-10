package com.dww.insurance.repository;

import com.dww.insurance.domain.DriverInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DriverInfoRepository {

    private static final String INSERT_STATEMENT =
        "insert into owner (name, pass_id, last_name, address, phone) VALUES (?,?,?,?,?)";
    private static final String UPDATE_STATEMENT =
        "update owner SET name = ?, pass_id = ?, last_name = ?, address = ?, phone = ? WHERE id = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM owner WHERE id = ?";

    public int insert(DriverInfo driverInfo) {
        Connection conn = null;
        PreparedStatement stmt;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, driverInfo.getName());
            stmt.setString(2, driverInfo.getPassId());
            stmt.setString(3, driverInfo.getLastName());
            stmt.setString(4, driverInfo.getAddress());
            stmt.setString(5, driverInfo.getPhone());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            ConnectionPool.getInstance().releaseConnection(conn);
        }
    }

    public void update(DriverInfo driverInfo) {
        Connection conn = null;
        PreparedStatement stmt;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(UPDATE_STATEMENT);
            stmt.setString(1, driverInfo.getName());
            stmt.setString(2, driverInfo.getPassId());
            stmt.setString(3, driverInfo.getLastName());
            stmt.setString(4, driverInfo.getAddress());
            stmt.setString(5, driverInfo.getPhone());
            stmt.setInt(6, driverInfo.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            ConnectionPool.getInstance().releaseConnection(conn);
        }
    }

    public void delete(int driverInfoId) {
        Connection conn = null;
        PreparedStatement stmt;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(DELETE_STATEMENT);
            stmt.setInt(1, driverInfoId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            ConnectionPool.getInstance().releaseConnection(conn);
        }
    }

}
