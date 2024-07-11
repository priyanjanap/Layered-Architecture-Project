package Lk.ijse.Dress.dao.Custom.impl;

import Lk.ijse.Dress.Entity.Order;
import Lk.ijse.Dress.Entity.OrderTable;
import Lk.ijse.Dress.dao.Custom.OrderDAO;
import Lk.ijse.Dress.dao.SQLUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public List<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Order order) throws SQLException {
        String sql = "INSERT INTO orders (order_id, order_date, customer_id, payment_id) VALUES (?, ?, ?, ?)";

        try {
            return SQLUtil.execute(sql, order.getOrderId(), order.getDate(), order.getCusId(), order.getPaymentId());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
@Override
    public  String getCurrentId() throws SQLException ,ClassNotFoundException {
        String sql = "SELECT Order_Id FROM orders ORDER BY Order_Id DESC LIMIT 1";
        try (ResultSet resultSet = SQLUtil.execute(sql)) {
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
            return null;
        }
    }

    @Override
    public int getOrderCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) AS order_count FROM orders";
        try (
             ResultSet resultSet = SQLUtil.execute(sql)) {
            if (resultSet.next()) {
                return resultSet.getInt("order_count");
            }
        }
        return 0;
    }

    @Override
    public ObservableList<OrderTable> loadRentalAndOrdersData() throws SQLException, ClassNotFoundException {
        ObservableList<OrderTable> ordersList = FXCollections.observableArrayList();

        String joinQuery = "SELECT o.Order_Id, o.order_date, c.Customer_Id, c.Customer_name, c.Customer_contact_number " +
                "FROM orders o JOIN customer c ON o.Customer_Id = c.Customer_Id";

        ResultSet resultSet=SQLUtil.execute(joinQuery);

            while (resultSet.next()) {
                String orderId = resultSet.getString("Order_Id");
                Date orderDate = resultSet.getDate("order_date");
                String customerId = resultSet.getString("Customer_Id");
                String customerName = resultSet.getString("Customer_name");
                int customerContactNumber = resultSet.getInt("Customer_contact_number");

                OrderTable order = new OrderTable(orderId, orderDate, customerId, customerName, customerContactNumber);
                ordersList.add(order);
            }


        return ordersList;
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
