package Lk.ijse.Dress.dao.Custom;

import Lk.ijse.Dress.dao.SuperDAO;

import java.sql.SQLException;

public interface QueryDAO extends SuperDAO {
    int countByNic(String nic) throws SQLException, ClassNotFoundException;

}