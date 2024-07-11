package Lk.ijse.Dress.dao.Custom;

import Lk.ijse.Dress.Entity.PaymentOrder;
import Lk.ijse.Dress.Model.Enum.PaymentType;
import Lk.ijse.Dress.dao.CrudDAO;

import java.sql.SQLException;
import java.util.List;

public interface PaymentOrderDAO extends CrudDAO<PaymentOrder> {
    List<PaymentOrder> getPaymentOrdersByNic(String nic) throws SQLException,ClassNotFoundException;
    public boolean update2(String nic, double amount, PaymentType paymentType) throws SQLException, ClassNotFoundException;
}
