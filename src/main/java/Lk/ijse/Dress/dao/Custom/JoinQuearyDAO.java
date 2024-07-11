package Lk.ijse.Dress.dao.Custom;

import Lk.ijse.Dress.Entity.RentalTable;
import Lk.ijse.Dress.dao.SuperDAO;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface JoinQuearyDAO extends SuperDAO {
    public ObservableList<RentalTable> loadRentalAndOrdersData() throws SQLException,ClassNotFoundException;
}
