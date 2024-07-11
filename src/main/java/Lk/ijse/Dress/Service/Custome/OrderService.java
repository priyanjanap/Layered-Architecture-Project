package Lk.ijse.Dress.Service.Custome;

import Lk.ijse.Dress.DTO.OrderDTO;
import Lk.ijse.Dress.Entity.OrderTable;
import Lk.ijse.Dress.Service.AllService;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface OrderService extends AllService {
    boolean add(OrderDTO order) throws SQLException,ClassNotFoundException;
     String getCurrentId() throws SQLException ,ClassNotFoundException;
    public int getOrderCount() throws SQLException, ClassNotFoundException;
    public ObservableList<OrderTable> getOrders()throws  SQLException,ClassNotFoundException;
}
