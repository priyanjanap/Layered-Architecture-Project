package Lk.ijse.Dress.dao.Custom;

import Lk.ijse.Dress.Entity.Rental;
import Lk.ijse.Dress.Entity.RentalDress;
import Lk.ijse.Dress.dao.CrudDAO;

import java.sql.SQLException;
import java.util.List;

public interface RentalDAO extends CrudDAO <Rental>{
    public  String getAllRentalIds() throws SQLException;
    public int getRentalCount() throws SQLException,ClassNotFoundException;
    public  boolean save(List<RentalDress> odList) throws SQLException, ClassNotFoundException;


}
