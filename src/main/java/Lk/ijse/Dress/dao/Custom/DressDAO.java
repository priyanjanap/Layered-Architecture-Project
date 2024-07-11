package Lk.ijse.Dress.dao.Custom;

import Lk.ijse.Dress.Entity.Dress;
import Lk.ijse.Dress.dao.CrudDAO;

import java.sql.SQLException;
import java.util.List;

public interface DressDAO extends CrudDAO<Dress> {
    List<String> getALLDressIDS() throws SQLException, ClassNotFoundException;
}
