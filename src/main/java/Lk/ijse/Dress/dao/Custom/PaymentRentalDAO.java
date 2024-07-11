package Lk.ijse.Dress.dao.Custom;

import Lk.ijse.Dress.Entity.PaymentRental;
import Lk.ijse.Dress.dao.SuperDAO;

import java.sql.SQLException;

public interface PaymentRentalDAO extends SuperDAO {
    public boolean add(PaymentRental paymentRental) throws SQLException, ClassNotFoundException;
}
