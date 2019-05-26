package com.dww.insurance.service;

import com.dww.insurance.domain.DamageInfo;
import com.dww.insurance.domain.DriverInfo;
import com.dww.insurance.domain.VehicleInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.Properties;
import java.util.Random;

public class DriverRepository {

    private Properties props = AppProperties.getInstance().getAppProps();

    public int insertDriverInfo(DriverInfo driverInfo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("password"));
            stmt = conn.prepareStatement("INSERT INTO owner (name, pass_id, last_name, address, phone) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
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
            closeConnection(conn, stmt);
        }
    }

    public void updateDriverInfo(DriverInfo driverInfo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("password"));
            stmt = conn.prepareStatement("UPDATE owner SET name = ?, pass_id = ?, last_name = ?, address = ?, phone = ? WHERE id = ?");
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
            closeConnection(conn, stmt);
        }
    }

    public void deleteDriverInfo(int driverInfoId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("password"));
            stmt = conn.prepareStatement("DELETE FROM owner WHERE id = ?");
            stmt.setInt(1, driverInfoId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            closeConnection(conn, stmt);
        }
    }

    public int insertVehicleInfo(int driverId, VehicleInfo vehicleInfo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("password"));
            stmt = conn.prepareStatement("INSERT INTO vehicle (owner_id, type, body_number, model, number) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
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
            closeConnection(conn, stmt);
        }
    }


    public void updateVehicleInfo(VehicleInfo vehicleInfo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("password"));
            stmt = conn.prepareStatement("UPDATE vehicle SET type = ?, body_number = ?, model = ?, number = ? WHERE id = ? AND owner_id = ?");
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
            closeConnection(conn, stmt);
        }
    }

    public void deleteVehicleInfo(int vehicleInfoId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("password"));
            stmt = conn.prepareStatement("DELETE FROM vehicle WHERE id = ?");
            stmt.setInt(1, vehicleInfoId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            closeConnection(conn, stmt);
        }
    }

    public void insertDamageInfo(int vehicleId, DamageInfo damageInfo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("password"));
            stmt = conn.prepareStatement("INSERT INTO damage (vehicle_id, date, zone1, zone2, zone3, zone4, zone5, zone6, zone7, zone8, zone9, zone10, zone11, zone12, zone13) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, vehicleId);
            stmt.setDate(2, new Date(System.currentTimeMillis()));
            stmt.setBoolean(3, damageInfo.getDamage().getDamageZone()[0]);
            stmt.setBoolean(4, damageInfo.getDamage().getDamageZone()[1]);
            stmt.setBoolean(5, damageInfo.getDamage().getDamageZone()[2]);
            stmt.setBoolean(6, damageInfo.getDamage().getDamageZone()[3]);
            stmt.setBoolean(7, damageInfo.getDamage().getDamageZone()[4]);
            stmt.setBoolean(8, damageInfo.getDamage().getDamageZone()[5]);
            stmt.setBoolean(9, damageInfo.getDamage().getDamageZone()[6]);
            stmt.setBoolean(10, damageInfo.getDamage().getDamageZone()[7]);
            stmt.setBoolean(11, damageInfo.getDamage().getDamageZone()[8]);
            stmt.setBoolean(12, damageInfo.getDamage().getDamageZone()[9]);
            stmt.setBoolean(13, damageInfo.getDamage().getDamageZone()[10]);
            stmt.setBoolean(14, damageInfo.getDamage().getDamageZone()[11]);
            stmt.setBoolean(15, damageInfo.getDamage().getDamageZone()[12]);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            closeConnection(conn, stmt);
        }
    }

    public void updateDamageInfo(int vehicleId, DamageInfo damageInfo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("password"));
            stmt = conn.prepareStatement("UPDATE damage SET date = ?, zone1 = ?, zone2 = ?, zone3 = ?, zone4 = ?, zone5 = ?, zone6 = ?, zone7 = ?, zone8 = ?, zone9 = ?, zone10 = ?, zone11 = ?, zone12 = ?, zone13 = ? WHERE id = ? AND vehicle_id = ?");
            stmt.setDate(1, new Date(System.currentTimeMillis()));
            stmt.setBoolean(2, damageInfo.getDamage().getDamageZone()[0]);
            stmt.setBoolean(3, damageInfo.getDamage().getDamageZone()[1]);
            stmt.setBoolean(4, damageInfo.getDamage().getDamageZone()[2]);
            stmt.setBoolean(5, damageInfo.getDamage().getDamageZone()[3]);
            stmt.setBoolean(6, damageInfo.getDamage().getDamageZone()[4]);
            stmt.setBoolean(7, damageInfo.getDamage().getDamageZone()[5]);
            stmt.setBoolean(8, damageInfo.getDamage().getDamageZone()[6]);
            stmt.setBoolean(9, damageInfo.getDamage().getDamageZone()[7]);
            stmt.setBoolean(10, damageInfo.getDamage().getDamageZone()[8]);
            stmt.setBoolean(11, damageInfo.getDamage().getDamageZone()[9]);
            stmt.setBoolean(12, damageInfo.getDamage().getDamageZone()[10]);
            stmt.setBoolean(13, damageInfo.getDamage().getDamageZone()[11]);
            stmt.setBoolean(14, damageInfo.getDamage().getDamageZone()[12]);
            stmt.setInt(15, damageInfo.getId());
            stmt.setInt(16, vehicleId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            closeConnection(conn, stmt);
        }
    }

    public void deleteDamageInfo(int damageInfoId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("password"));
            stmt = conn.prepareStatement("DELETE FROM damage WHERE id = ?");
            stmt.setInt(1, damageInfoId);
            stmt.executeUpdate();
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
}
