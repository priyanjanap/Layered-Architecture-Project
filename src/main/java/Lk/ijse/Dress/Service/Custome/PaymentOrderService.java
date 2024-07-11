package Lk.ijse.Dress.Service.Custome;

import Lk.ijse.Dress.DTO.PaymentOrderDTO;
import Lk.ijse.Dress.Model.Enum.PaymentType;
import Lk.ijse.Dress.Service.AllService;

import java.sql.SQLException;
import java.util.List;

public interface PaymentOrderService extends AllService {
    List<PaymentOrderDTO> getPaymentOrdersByNic(String nic) throws SQLException,ClassNotFoundException;
    List<PaymentOrderDTO> getAll() throws SQLException, ClassNotFoundException;
    boolean add(PaymentOrderDTO paymentOrder) throws SQLException, ClassNotFoundException;
    public boolean update(PaymentOrderDTO paymentOrder) throws SQLException, ClassNotFoundException;
    public boolean update2(String nic, double amount, PaymentType paymentType) throws SQLException, ClassNotFoundException;
}
