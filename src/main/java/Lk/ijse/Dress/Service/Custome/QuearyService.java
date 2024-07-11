package Lk.ijse.Dress.Service.Custome;

import Lk.ijse.Dress.Service.AllService;

import java.sql.SQLException;

public interface QuearyService extends AllService {
    public int countByNic(String nic) throws SQLException, ClassNotFoundException;
}
