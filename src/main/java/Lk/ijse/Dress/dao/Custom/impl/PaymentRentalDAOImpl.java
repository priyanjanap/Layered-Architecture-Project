package Lk.ijse.Dress.dao.Custom.impl;

import Lk.ijse.Dress.Entity.PaymentRental;
import Lk.ijse.Dress.dao.Custom.PaymentRentalDAO;
import Lk.ijse.Dress.dao.SQLUtil;

import java.sql.SQLException;

public class PaymentRentalDAOImpl implements PaymentRentalDAO {
    @Override
    public boolean add(PaymentRental paymentOrder) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO PaymentOrder (payid, cusid, name, total, nic, paymentType, amount) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, paymentOrder.getPayid(), paymentOrder.getCusid(), paymentOrder.getCusname(), paymentOrder.getTotal(), paymentOrder.getNic(), paymentOrder.getPaymentType().toString(), paymentOrder.getAmount());
    }
    }
