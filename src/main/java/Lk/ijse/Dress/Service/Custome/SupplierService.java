package Lk.ijse.Dress.Service.Custome;

import Lk.ijse.Dress.DTO.SupplierDTO;
import Lk.ijse.Dress.Service.AllService;

import java.sql.SQLException;
import java.util.List;

public interface SupplierService extends AllService {
    SupplierDTO getById(String supplierId) throws SQLException, ClassNotFoundException;
    List< SupplierDTO> getAll() throws SQLException, ClassNotFoundException;
    boolean add( SupplierDTO supplier) throws SQLException, ClassNotFoundException;
    boolean update( SupplierDTO supplier) throws SQLException, ClassNotFoundException;
    boolean delete(String supplierId) throws SQLException, ClassNotFoundException;
    SupplierDTO search(String id) throws SQLException, ClassNotFoundException;
    List<String> getALLSupplierIDS() throws SQLException, ClassNotFoundException;

    public int getSupplierCount() throws  SQLException,ClassNotFoundException;
}
