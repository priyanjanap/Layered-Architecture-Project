package Lk.ijse.Dress.dao.Custom;

import Lk.ijse.Dress.Entity.Product;
import Lk.ijse.Dress.dao.CrudDAO;

import java.sql.SQLException;

public interface ProductDAO extends CrudDAO <Product>{
    public Product search(String id) throws SQLException, ClassNotFoundException;
}
