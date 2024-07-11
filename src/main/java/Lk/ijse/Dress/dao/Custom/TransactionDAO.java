package Lk.ijse.Dress.dao.Custom;

import Lk.ijse.Dress.Entity.PlaceOrder;
import Lk.ijse.Dress.Entity.PlaceRental;
import Lk.ijse.Dress.dao.SuperDAO;

import java.sql.SQLException;

public interface TransactionDAO extends SuperDAO {
    public boolean placeOrder(PlaceOrder po) throws SQLException, ClassNotFoundException;
    public boolean placeRental(PlaceRental po) throws SQLException, ClassNotFoundException;
}
