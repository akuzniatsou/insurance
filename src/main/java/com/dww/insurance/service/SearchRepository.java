package com.dww.insurance.service;

import com.dww.insurance.domain.DamageReport;
import com.dww.insurance.domain.DriverInfo;
import com.dww.insurance.domain.Person;
import com.dww.insurance.domain.QueryParam;
import com.dww.insurance.domain.VehicleInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchRepository {

    String url = "jdbc:postgresql://localhost:5432/insurance";
    String user = "postgres";
    String password = "postgres123";



    public List<Person> find(QueryParam queryParam) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String surname = queryParam.getSurname();

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.prepareStatement("SELECT * FROM owner where name LIKE ?");
            stmt.setString(1, "%" + surname + "%");
            ResultSet rs = stmt.executeQuery();
            List<Person> persons = new ArrayList<>();
                while (rs.next()) {
                    persons.add(new Person(rs.getInt(1), rs.getString(2), rs.getString(3)));
                }
                return persons;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        finally {
            try {
                if (stmt != null) { stmt.close(); }
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            try {
                if (conn != null) { conn.close(); }
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }

    public List<Person> searchAll() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.prepareStatement("SELECT * FROM owner");
            ResultSet rs = stmt.executeQuery();
            List<Person> persons = new ArrayList<>();
            while (rs.next()) {
                persons.add(new Person(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            return persons;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        finally {
            try {
                if (stmt != null) { stmt.close(); }
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            try {
                if (conn != null) { conn.close(); }
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }

    public DamageReport searchDriverInfo(int ownerId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
//            stmt = conn.prepareStatement("SELECT * FROM owner JOIN vehicle v on owner.id = v.owner_id JOIN damage d on v.id = d.vehicle_id WHERE owner.id = ?");
            stmt = conn.prepareStatement("SELECT * FROM owner JOIN vehicle v on owner.id = v.owner_id WHERE owner.id = ?");
            stmt.setInt(1, ownerId);

            ResultSet rs = stmt.executeQuery();
            if (rs.wasNull()) {
                return null;
            }
            while (rs.next()) {
                DriverInfo driverInfo = new DriverInfo();
                driverInfo.setId(rs.getInt(1));
                driverInfo.setName(rs.getString(2));
                driverInfo.setPassId(rs.getString(3));
                driverInfo.setLastName(rs.getString(4));
                driverInfo.setAddress(rs.getString(5));
                driverInfo.setPhone(rs.getString(6));

                VehicleInfo vehicleInfo = new VehicleInfo();
                vehicleInfo.setId(rs.getInt(7));
                vehicleInfo.setType(rs.getString(9));
                vehicleInfo.setBodyId(rs.getString(11));
                vehicleInfo.setModel(rs.getString(12));

//                DamageInfo damageInfo = new DamageInfo();
//                damageInfo.setId(rs.getInt(9));
//                damageInfo.setDate(rs.getDate(11));
//                damageInfo.setDamage(new Damage(new boolean[]{
//                    rs.getBoolean(12),
//                    rs.getBoolean(13),
//                    rs.getBoolean(14)
//                }));

                DamageReport damageReport = new DamageReport();
                damageReport.setDriverInfo(driverInfo);
                damageReport.setVehicleInfo(vehicleInfo);
//                damageReport.setDamageInfo(damageInfo);
                return damageReport;
            }
            return null;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        finally {
            try {
                if (stmt != null) { stmt.close(); }
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            try {
                if (conn != null) { conn.close(); }
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }
}
