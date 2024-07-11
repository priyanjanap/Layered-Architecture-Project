package Lk.ijse.Dress.dao.Custom.impl;

import Lk.ijse.Dress.Entity.PaymentOrder;
import Lk.ijse.Dress.Model.Enum.PaymentType;
import Lk.ijse.Dress.dao.Custom.PaymentOrderDAO;
import Lk.ijse.Dress.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentOrderDAOImpl implements PaymentOrderDAO {

    @Override
    public List<PaymentOrder> getPaymentOrdersByNic(String nic) throws SQLException,ClassNotFoundException {
        String sql = "SELECT * FROM PaymentOrder WHERE nic = ?";
        List<PaymentOrder> paymentOrders = new ArrayList<>();

        try (ResultSet resultSet = SQLUtil.execute(sql, nic)) {
            while (resultSet.next()) {
                PaymentType paymentType = PaymentType.valueOf(resultSet.getString(7));
                PaymentOrder paymentOrder = new PaymentOrder(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4),resultSet.getString(6),paymentType,resultSet.getDouble(8));
                paymentOrders.add(paymentOrder);
            }
        }

        return paymentOrders;
    }



    @Override
    public List< PaymentOrder> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM PaymentOrder";
        List<PaymentOrder> paymentOrders = new ArrayList<>();

        try (ResultSet resultSet = SQLUtil.execute(sql)) {
            while (resultSet.next()) {
                PaymentType paymentType = PaymentType.valueOf(resultSet.getString(7));

                PaymentOrder paymentOrder= new PaymentOrder(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4),resultSet.getString(6),paymentType,resultSet.getDouble(8));
                paymentOrders.add(paymentOrder);
            }
        }

        return paymentOrders;
    }

    @Override
    public boolean add(PaymentOrder paymentOrder) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO PaymentOrder (payid, cusid, name, total, nic, paymentType, amount) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, paymentOrder.getPayid(), paymentOrder.getCusid(), paymentOrder.getName(), paymentOrder.getTotal(), paymentOrder.getNic(), paymentOrder.getPaymentType().toString(), paymentOrder.getAmount());
    }

    @Override
    public boolean update(PaymentOrder entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update2(String nic, double amount, PaymentType paymentType) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE PaymentOrder SET amount = ?, paymentType = ? WHERE nic = ?";
        return SQLUtil.execute(sql,amount,paymentType,nic);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
