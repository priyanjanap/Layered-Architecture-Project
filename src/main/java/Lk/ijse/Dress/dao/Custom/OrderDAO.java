package Lk.ijse.Dress.dao.Custom;

import Lk.ijse.Dress.Entity.Order;
import Lk.ijse.Dress.Entity.OrderTable;
import Lk.ijse.Dress.dao.CrudDAO;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO <Order>{
    public  String getCurrentId() throws SQLException,ClassNotFoundException;
     int getOrderCount() throws SQLException,ClassNotFoundException;
    public ObservableList<OrderTable> loadRentalAndOrdersData() throws SQLException,ClassNotFoundException;
}
