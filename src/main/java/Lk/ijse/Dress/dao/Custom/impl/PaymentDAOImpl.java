package Lk.ijse.Dress.dao.Custom.impl;

import Lk.ijse.Dress.Entity.Payment;
import Lk.ijse.Dress.dao.Custom.PaymentDAO;
import Lk.ijse.Dress.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean save(Payment od) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO payment (Payment_Id, Date, Amount) VALUES (?, ?, ?)";

        try {
            return SQLUtil.execute(sql, od.getPaymentId(), od.getDate(), od.getAmount());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean save1(List<Payment> odList) throws SQLException, ClassNotFoundException {
        for (Payment od : odList) {
            boolean isSaved = save(od);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getPaymentId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Payment_Id FROM payment ORDER BY Payment_Id DESC LIMIT 1";

        try (ResultSet resultSet = SQLUtil.execute(sql)) {
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
            return null;
        }
    }

    @Override
    public List<Payment> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Payment od) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
