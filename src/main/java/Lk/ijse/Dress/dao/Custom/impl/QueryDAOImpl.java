package Lk.ijse.Dress.dao.Custom.impl;

import Lk.ijse.Dress.dao.Custom.QueryDAO;
import Lk.ijse.Dress.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public int countByNic(String nic) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT COUNT(*) AS count FROM qr_codes WHERE nic_number = ?";
        try (ResultSet resultSet = SQLUtil.execute(sqlQuery, nic)) {
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
        }
        return 0;
    }
}
