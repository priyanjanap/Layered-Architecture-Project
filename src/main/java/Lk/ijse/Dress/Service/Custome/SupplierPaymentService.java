package Lk.ijse.Dress.Service.Custome;

import Lk.ijse.Dress.DTO.SupplierPaymentDTO;
import Lk.ijse.Dress.Service.AllService;

import java.sql.SQLException;
import java.util.List;

public interface SupplierPaymentService extends AllService {
    List<SupplierPaymentDTO> getAll() throws SQLException, ClassNotFoundException;
    boolean add(SupplierPaymentDTO supplierPayment) throws SQLException, ClassNotFoundException;
    boolean update(SupplierPaymentDTO supplierPayment) throws SQLException, ClassNotFoundException;
    boolean delete(String payId) throws SQLException, ClassNotFoundException;

}
