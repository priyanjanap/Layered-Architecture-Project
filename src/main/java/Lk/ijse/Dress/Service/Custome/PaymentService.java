package Lk.ijse.Dress.Service.Custome;

import Lk.ijse.Dress.Entity.Payment;
import Lk.ijse.Dress.Service.AllService;

import java.sql.SQLException;
import java.util.List;

public interface PaymentService extends AllService {
    boolean save(List<Payment> odList) throws SQLException, ClassNotFoundException;

    String getCurrentId() throws SQLException,ClassNotFoundException;
}
