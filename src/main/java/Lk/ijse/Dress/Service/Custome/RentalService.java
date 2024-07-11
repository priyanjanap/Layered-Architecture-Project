package Lk.ijse.Dress.Service.Custome;

import Lk.ijse.Dress.DTO.RentalDTO;
import Lk.ijse.Dress.Service.AllService;

import java.sql.SQLException;

public interface RentalService extends AllService {
    boolean add(RentalDTO rental) throws SQLException ,ClassNotFoundException;
    String getAllRentalIds() throws SQLException,ClassNotFoundException;
    boolean delete(String rentalId) throws SQLException,ClassNotFoundException;
    boolean update(RentalDTO rental) throws SQLException,ClassNotFoundException;
    public int getRentalCount() throws SQLException,ClassNotFoundException;
}
