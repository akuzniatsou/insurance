package com.dww.insurance.service;

import com.dww.insurance.domain.DriverInfo;
import com.dww.insurance.domain.VehicleInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.UUID;

public class DriverRepository {

    private Properties props = AppProperties.getInstance().getAppProps();


    public int insertDriverInfo(DriverInfo driverInfo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("password"));
            stmt = conn.prepareStatement("INSERT INTO owner (name, pass_id) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, driverInfo.getName());
            stmt.setString(2, driverInfo.getPassId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
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

    public void insertVehicleInfo(int driverId, VehicleInfo vehicleInfo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("password"));
            stmt = conn.prepareStatement("INSERT INTO vehicle (owner_id, type, body_number, number) VALUES (?,?,?,?)");
            stmt.setInt(1, driverId);
            stmt.setString(2, vehicleInfo.getType());
            stmt.setString(3, vehicleInfo.getBodyId());
            stmt.setString(4, UUID.randomUUID().toString());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
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
}
