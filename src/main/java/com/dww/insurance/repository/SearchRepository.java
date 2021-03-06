package com.dww.insurance.repository;

import com.dww.insurance.domain.DamageInfo;
import com.dww.insurance.dto.DamageReport;
import com.dww.insurance.domain.DriverInfo;
import com.dww.insurance.dto.QueryParam;
import com.dww.insurance.dto.SearchResult;
import com.dww.insurance.domain.VehicleInfo;
import com.dww.insurance.domain.VehicleType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchRepository {

    private static final String SELECT_STATEMENT =
        "SELECT * FROM owner JOIN vehicle v on owner.id = v.owner_id JOIN damage d on v.id = d.vehicle_id WHERE owner.id = ?";
    private static final String SELECT_ALL_STATEMENT =
        "select o.id, o.name, o.last_name, v.body_number from owner o join vehicle v on o.id = v.owner_id order by o.id";

    public List<SearchResult> find(QueryParam queryParam) {
        Connection conn = null;
        PreparedStatement stmt;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            List<Object> parameters = new ArrayList<>();
            StringBuilder query = new StringBuilder("select o.id, o.name, o.last_name, v.body_number from owner o join vehicle v on o.id = v.owner_id where 1=1");
            if (queryParam.getOwnerId() > 0) {
                query.append(" and o.id = ?");
                parameters.add(queryParam.getOwnerId());
            }
            if (!isBlank(queryParam.getSurname())) {
                query.append(" and o.last_name ilike ?");
                parameters.add(queryParam.getSurname());
            }
            if (!isBlank(queryParam.getBodyId())) {
                query.append(" and v.body_number like ?");
                parameters.add(queryParam.getBodyId());
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
            List<SearchResult> persons = new ArrayList<>();
            while (rs.next()) {
                persons.add(new SearchResult(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            return persons;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            ConnectionPool.getInstance().releaseConnection(conn);
        }
    }

    public List<SearchResult> searchAll() {
        Connection conn = null;
        PreparedStatement stmt;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(SELECT_ALL_STATEMENT);
            ResultSet rs = stmt.executeQuery();
            List<SearchResult> persons = new ArrayList<>();
            while (rs.next()) {
                persons.add(new SearchResult(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            return persons;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            ConnectionPool.getInstance().releaseConnection(conn);
        }

    }

    public DamageReport searchDriverInfo(int ownerId) {
        Connection conn = null;
        PreparedStatement stmt;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn.prepareStatement(SELECT_STATEMENT);
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
                vehicleInfo.setType(VehicleType.valueOf(rs.getString(9)));
                vehicleInfo.setNumber(rs.getString(10));
                vehicleInfo.setBodyId(rs.getString(11));
                vehicleInfo.setModel(rs.getString(12));

                DamageInfo damageInfo = new DamageInfo(new boolean[13]);
                damageInfo.setId(rs.getInt(13));
                damageInfo.setDate(rs.getDate(15));
                damageInfo.setDamageZone(new boolean[]{
                    rs.getBoolean(16),
                    rs.getBoolean(17),
                    rs.getBoolean(18),

                    rs.getBoolean(19),
                    rs.getBoolean(20),
                    rs.getBoolean(21),

                    rs.getBoolean(22),
                    rs.getBoolean(23),
                    rs.getBoolean(24),

                    rs.getBoolean(25),
                    rs.getBoolean(26),
                    rs.getBoolean(27),

                    rs.getBoolean(28)
                });

                DamageReport damageReport = new DamageReport();
                damageReport.setDriverInfo(driverInfo);
                damageReport.setVehicleInfo(vehicleInfo);
                damageReport.setDamageInfo(damageInfo);
                return damageReport;
            }
            return null;

        } catch (SQLException ex) {
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
