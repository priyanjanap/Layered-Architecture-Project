package Lk.ijse.Dress.dao.Custom;

import Lk.ijse.Dress.Entity.Customer;
import Lk.ijse.Dress.dao.CrudDAO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO <Customer>{


    public Customer search(String id) throws SQLException, ClassNotFoundException;
    public List<String> getALLCustomerIDS() throws SQLException, ClassNotFoundException;
    public int getCustomerCount() throws SQLException,ClassNotFoundException;
}
