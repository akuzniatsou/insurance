package com.dww.insurance.repository;

import com.dww.insurance.domain.VehicleInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VehicleInfoRepository {

    private static final String INSERT_STATEMENT =
        "INSERT INTO vehicle (owner_id, type, body_number, model, number) VALUES (?,?,?,?,?)";
    private static final String UPDATE_STATEMENT =
        "UPDATE vehicle SET type = ?, body_number = ?, model = ?, number = ? WHERE id = ? AND owner_id = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM vehicle WHERE id = ?";

    public int insert(int driverId, VehicleInfo vehicleInfo) {
        Connection conn = null;
        PreparedStatement stmt;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, driverId);
            stmt.setString(2, vehicleInfo.getType().name());
            stmt.setString(3, vehicleInfo.getBodyId());
            stmt.setString(4, vehicleInfo.getModel());
            stmt.setString(5, vehicleInfo.getNumber());
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

    public void update(VehicleInfo vehicleInfo) {
        Connection conn = null;
        PreparedStatement stmt;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(UPDATE_STATEMENT);
            stmt.setString(1, vehicleInfo.getType().name());
            stmt.setString(2, vehicleInfo.getBodyId());
            stmt.setString(3, vehicleInfo.getModel());
            stmt.setString(4, vehicleInfo.getNumber());
            stmt.setInt(5, vehicleInfo.getId());
            stmt.setInt(6, vehicleInfo.getOwner_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            ConnectionPool.getInstance().releaseConnection(conn);
        }
    }

    public void delete(int vehicleInfoId) {
        Connection conn = null;
        PreparedStatement stmt;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(DELETE_STATEMENT);
            stmt.setInt(1, vehicleInfoId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            ConnectionPool.getInstance().releaseConnection(conn);
        }
    }
}
