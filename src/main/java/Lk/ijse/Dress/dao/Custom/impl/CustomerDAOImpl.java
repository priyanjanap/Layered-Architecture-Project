package Lk.ijse.Dress.dao.Custom.impl;

import Lk.ijse.Dress.Entity.Customer;
import Lk.ijse.Dress.dao.Custom.CustomerDAO;
import Lk.ijse.Dress.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM customer";

        try (ResultSet rst = SQLUtil.execute(sql)) {
            List<Customer> allCustomers = new ArrayList<>();

            while (rst.next()) {
                Customer customer = new Customer(rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getInt(4),
                        rst.getString(5)
                );

                allCustomers.add(customer);
            }

            return allCustomers;

        }
    }

    @Override
    public boolean add(Customer customer) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO customer VALUES(?, ?, ?, ?,?)", customer.getCustomer_Id(), customer.getCustomer_name(), customer.getCustomer_Address(), customer.getCustomer_contact_Number(), customer.getEmail());
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE customer SET Customer_name = ?, Customer_Address = ?, Customer_contact_number = ?, email = ? WHERE Customer_Id = ?";
        return SQLUtil.execute(sql, customer.getCustomer_name(), customer.getCustomer_Address(), customer.getCustomer_contact_Number(), customer.getEmail(), customer.getCustomer_Id());
    }

    @Override
    public boolean delete(String customerId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM customer WHERE Customer_Id = ?";
        return SQLUtil.execute(sql, customerId);
    }

    @Override
    public Customer search(String customerId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM customer WHERE Customer_Id = ?";
        ResultSet rst = SQLUtil.execute(sql, customerId);
        rst.next();
        return new Customer(customerId, rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5));

    }

    /*
     public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE id=?", id + "");
        rst.next();
        return new Customer(id + "", rst.getString("name"), rst.getString("address"));
    }

     */
    @Override
    public List<String> getALLCustomerIDS() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Customer_Id FROM customer";
        try (ResultSet resultSet = SQLUtil.execute(sql)) {
            List<String> idList = new ArrayList<>();
            while (resultSet.next()) {
                idList.add(resultSet.getString(1));
            }
            return idList;
        }
    }

    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) AS customer_count FROM customer";
        try (
             ResultSet resultSet = SQLUtil.execute(sql)) {
            if (resultSet.next()) {
                return resultSet.getInt("customer_count");
            }
        }
        return 0;
    }
    }


