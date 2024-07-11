package Lk.ijse.Dress.dao.Custom.impl;

import Lk.ijse.Dress.Entity.SupplierPayment;
import Lk.ijse.Dress.dao.Custom.SupplierPaymentDAO;
import Lk.ijse.Dress.dao.SQLUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierPaymentDAOImpl implements SupplierPaymentDAO {
    @Override
    public List<SupplierPayment> getAll() throws SQLException, ClassNotFoundException {
        List<SupplierPayment> supplierPayments = new ArrayList<>();
        String sql = "SELECT * FROM supplierpayment";

        try (ResultSet resultSet = SQLUtil.execute(sql)) {
            while (resultSet.next()) {
                String payId = resultSet.getString("PayID");
                String supplierId = resultSet.getString("Supplier_Id");
                String supplierName = resultSet.getString("supplier_name");
                double amount = resultSet.getDouble("amount");
                Date date = resultSet.getDate("date");

                SupplierPayment supplierPayment = new SupplierPayment(payId, supplierId, supplierName, amount, date.toLocalDate());
                supplierPayments.add(supplierPayment);
            }
        }

        return supplierPayments;
    }

    @Override
    public boolean add(SupplierPayment supplierPayment) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO supplierpayment (PayID, Supplier_Id, supplier_name, amount, date) VALUES (?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, supplierPayment.getId1(), supplierPayment.getId2(), supplierPayment.getName(), supplierPayment.getAmount(), supplierPayment.getDate());
    }

    @Override
    public boolean update(SupplierPayment supplierPayment) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE supplierpayment SET Supplier_Id = ?, supplier_name = ?, amount = ?, date = ? WHERE PayID = ?";
        return SQLUtil.execute(sql, supplierPayment.getId2(), supplierPayment.getName(), supplierPayment.getAmount(), supplierPayment.getDate(), supplierPayment.getId1());
    }

    @Override
    public boolean delete(String payId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM supplierpayment WHERE PayID = ?";
        return SQLUtil.execute(sql, payId);
    }
}
