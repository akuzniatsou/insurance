package com.dww.insurance.repository;

import com.dww.insurance.domain.DamageInfo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DamageInfoRepository {

    private static final String UPDATE_STATEMENT =
        "UPDATE damage SET date = ?, zone1 = ?, zone2 = ?, zone3 = ?, zone4 = ?, zone5 = ?, zone6 = ?, zone7 = ?," +
            " zone8 = ?, zone9 = ?, zone10 = ?, zone11 = ?, zone12 = ?, zone13 = ? WHERE id = ? AND vehicle_id = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM damage WHERE id = ?";
    private static final String INSERT_STATEMENT =
        "INSERT INTO damage (vehicle_id, date, zone1, zone2, zone3, zone4, zone5, zone6, zone7, zone8, zone9," +
            " zone10, zone11, zone12, zone13) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public void insert(int vehicleId, DamageInfo damageInfo) {
        Connection conn = null;
        PreparedStatement stmt;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(INSERT_STATEMENT);
            stmt.setInt(1, vehicleId);
            stmt.setDate(2, new Date(System.currentTimeMillis()));
            stmt.setBoolean(3, damageInfo.getDamageZone()[0]);
            stmt.setBoolean(4, damageInfo.getDamageZone()[1]);
            stmt.setBoolean(5, damageInfo.getDamageZone()[2]);
            stmt.setBoolean(6, damageInfo.getDamageZone()[3]);
            stmt.setBoolean(7, damageInfo.getDamageZone()[4]);
            stmt.setBoolean(8, damageInfo.getDamageZone()[5]);
            stmt.setBoolean(9, damageInfo.getDamageZone()[6]);
            stmt.setBoolean(10, damageInfo.getDamageZone()[7]);
            stmt.setBoolean(11, damageInfo.getDamageZone()[8]);
            stmt.setBoolean(12, damageInfo.getDamageZone()[9]);
            stmt.setBoolean(13, damageInfo.getDamageZone()[10]);
            stmt.setBoolean(14, damageInfo.getDamageZone()[11]);
            stmt.setBoolean(15, damageInfo.getDamageZone()[12]);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            ConnectionPool.getInstance().releaseConnection(conn);
        }
    }

    public void update(int vehicleId, DamageInfo damageInfo) {
        Connection conn = null;
        PreparedStatement stmt;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(UPDATE_STATEMENT);
            stmt.setDate(1, new Date(System.currentTimeMillis()));
            stmt.setBoolean(2, damageInfo.getDamageZone()[0]);
            stmt.setBoolean(3, damageInfo.getDamageZone()[1]);
            stmt.setBoolean(4, damageInfo.getDamageZone()[2]);
            stmt.setBoolean(5, damageInfo.getDamageZone()[3]);
            stmt.setBoolean(6, damageInfo.getDamageZone()[4]);
            stmt.setBoolean(7, damageInfo.getDamageZone()[5]);
            stmt.setBoolean(8, damageInfo.getDamageZone()[6]);
            stmt.setBoolean(9, damageInfo.getDamageZone()[7]);
            stmt.setBoolean(10, damageInfo.getDamageZone()[8]);
            stmt.setBoolean(11, damageInfo.getDamageZone()[9]);
            stmt.setBoolean(12, damageInfo.getDamageZone()[10]);
            stmt.setBoolean(13, damageInfo.getDamageZone()[11]);
            stmt.setBoolean(14, damageInfo.getDamageZone()[12]);
            stmt.setInt(15, damageInfo.getId());
            stmt.setInt(16, vehicleId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            ConnectionPool.getInstance().releaseConnection(conn);
        }
    }

    public void delete(int damageInfoId) {
        Connection conn = null;
        PreparedStatement stmt;

        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(DELETE_STATEMENT);
            stmt.setInt(1, damageInfoId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            ConnectionPool.getInstance().releaseConnection(conn);
        }
    }
}
