package Lk.ijse.Dress.Service.Custome;

import Lk.ijse.Dress.Entity.PlaceOrder;
import Lk.ijse.Dress.Entity.PlaceRental;
import Lk.ijse.Dress.Service.AllService;

import java.sql.SQLException;

public interface TransactionService extends AllService {
    public boolean placeOrder(PlaceOrder po)throws SQLException,ClassNotFoundException;
    public boolean placeRental(PlaceRental po) throws SQLException, ClassNotFoundException;
}
