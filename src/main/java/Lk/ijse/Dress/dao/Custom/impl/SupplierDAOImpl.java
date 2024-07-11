package Lk.ijse.Dress.dao.Custom.impl;

import Lk.ijse.Dress.Entity.Supplier;
import Lk.ijse.Dress.dao.Custom.SupplierDAO;
import Lk.ijse.Dress.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
@Override
    public  Supplier getById(String supplierId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM supplier WHERE Supplier_Id = ?";
        try (ResultSet resultSet = SQLUtil.execute(sql, supplierId)) {
            if (resultSet.next()) {
                return new Supplier(resultSet.getString("Supplier_Id"), resultSet.getString("Supplier_name"), resultSet.getString("Supplier_address"), resultSet.getInt("contact_number"));
            } else {
                return null;
            }
        }
    }

    @Override
    public int getSupplierCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM supplier";


             ResultSet resultSet = SQLUtil.execute(sql);

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        return 0;
    }

    @Override
    public List<Supplier> getAll() throws SQLException, ClassNotFoundException {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM supplier";
        try (ResultSet resultSet = SQLUtil.execute(sql)) {
            while (resultSet.next()) {
                String supplierId = resultSet.getString("Supplier_Id");
                String supplierName = resultSet.getString("Supplier_name");
                String supplierAddress = resultSet.getString("Supplier_address");
                int contactNumber = resultSet.getInt("contact_number");

                Supplier supplier = new Supplier(supplierId, supplierName, supplierAddress, contactNumber);
                suppliers.add(supplier);
            }
        }
        return suppliers;
    }

    @Override
    public boolean add(Supplier supplier) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO supplier (Supplier_Id, Supplier_name, Supplier_address, contact_number) VALUES (?, ?, ?, ?)";
        return SQLUtil.execute(sql, supplier.getSupplierId(), supplier.getSupplierName(), supplier.getSupplierAddress(), supplier.getContactNumber());
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE supplier SET ");
        List<Object> params = new ArrayList<>();

        if (supplier.getSupplierName() != null) {
            sqlBuilder.append("Supplier_name = ?, ");
            params.add(supplier.getSupplierName());
        }
        if (supplier.getSupplierAddress() != null) {
            sqlBuilder.append("Supplier_address = ?, ");
            params.add(supplier.getSupplierAddress());
        }
        if (supplier.getContactNumber() != 0) {
            sqlBuilder.append("contact_number = ?, ");
            params.add(supplier.getContactNumber());
        }

        sqlBuilder.setLength(sqlBuilder.length() - 2);
        sqlBuilder.append(" WHERE Supplier_Id = ?");
        params.add(supplier.getSupplierId());

        String sql = sqlBuilder.toString();
        return SQLUtil.execute(sql, params.toArray());
    }

    @Override
    public boolean delete(String supplierId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM supplier WHERE Supplier_Id = ?";
        return SQLUtil.execute(sql, supplierId);
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT Supplier_Id, Supplier_name, Supplier_address, contact_number FROM supplier WHERE Supplier_Id = ?";
        try (ResultSet resultSet = SQLUtil.execute(sql, id)) {
            if (resultSet.next()) {
                String supplierId = resultSet.getString("Supplier_Id");
                String supplierName = resultSet.getString("Supplier_name");
                String supplierAddress = resultSet.getString("Supplier_address");
                int contactNumber = resultSet.getInt("contact_number");

                return new Supplier(supplierId, supplierName, supplierAddress, contactNumber);
            }
        }
        return null;
    }

    @Override
    public List<String> getALLSupplierIDS() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Supplier_Id FROM supplier";
        List<String> supplierIds = new ArrayList<>();
        try (ResultSet resultSet = SQLUtil.execute(sql)) {
            while (resultSet.next()) {
                supplierIds.add(resultSet.getString("Supplier_Id"));
            }
        }
        return supplierIds;
    }
}
