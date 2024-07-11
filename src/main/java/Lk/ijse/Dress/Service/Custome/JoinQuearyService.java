package Lk.ijse.Dress.Service.Custome;

import Lk.ijse.Dress.Entity.RentalTable;
import Lk.ijse.Dress.Service.AllService;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface JoinQuearyService extends AllService {
    public ObservableList<RentalTable> getRentals()throws SQLException,ClassNotFoundException;
}
