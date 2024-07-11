package Lk.ijse.Dress.Service.Custome.Impl;

import Lk.ijse.Dress.DTO.OrderDTO;
import Lk.ijse.Dress.Entity.Order;
import Lk.ijse.Dress.Entity.OrderTable;
import Lk.ijse.Dress.Service.Custome.OrderService;
import Lk.ijse.Dress.dao.Custom.OrderDAO;
import Lk.ijse.Dress.dao.DAOFactory;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class OrderServiceImpl implements OrderService {
    OrderDAO orderDAO= (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    @Override
    public boolean add(OrderDTO order) throws SQLException, ClassNotFoundException {
        return orderDAO.add(new Order(order.getOrderId(),order.getDate(),order.getCusId(),order.getPaymentId()));
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return orderDAO.getCurrentId();
    }

    @Override
    public int getOrderCount() throws SQLException, ClassNotFoundException {
        return  orderDAO.getOrderCount();
    }

    @Override
    public ObservableList<OrderTable> getOrders() throws SQLException, ClassNotFoundException {
        try {
            return orderDAO.loadRentalAndOrdersData();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
