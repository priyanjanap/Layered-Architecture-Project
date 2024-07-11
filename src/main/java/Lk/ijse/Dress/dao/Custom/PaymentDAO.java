package Lk.ijse.Dress.dao.Custom;

import Lk.ijse.Dress.Entity.Payment;
import Lk.ijse.Dress.dao.CrudDAO;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment> {
    boolean save(Payment od) throws SQLException, ClassNotFoundException;

    boolean save1(List<Payment> odList) throws SQLException,ClassNotFoundException;

    String getPaymentId() throws  SQLException,ClassNotFoundException;
}
