package Lk.ijse.Dress.dao.Custom;

import Lk.ijse.Dress.Entity.Supplier;
import Lk.ijse.Dress.dao.CrudDAO;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDAO extends CrudDAO <Supplier>{
    public Supplier search(String id) throws SQLException, ClassNotFoundException;
    public List<String> getALLSupplierIDS() throws SQLException, ClassNotFoundException;
    Supplier getById(String supplierId) throws SQLException, ClassNotFoundException;
    public int getSupplierCount() throws SQLException,ClassNotFoundException;

}
