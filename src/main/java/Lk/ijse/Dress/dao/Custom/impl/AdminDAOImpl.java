package Lk.ijse.Dress.dao.Custom.impl;

import Lk.ijse.Dress.Entity.Admin;
import Lk.ijse.Dress.dao.Custom.AdminDAO;
import Lk.ijse.Dress.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public boolean whereUserName(String username) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) AS count FROM useraccount WHERE Username = ?";
        try (ResultSet resultSet = SQLUtil.execute(sql, username)) {
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        }
        return false;
    }

    @Override
    public boolean changePassword(String username, String password) throws SQLException, ClassNotFoundException {
        if (whereUserName(username)) {
            String sql = "UPDATE useraccount SET password = ? WHERE Username = ?";
            int rowsUpdated = SQLUtil.execute(sql, password, username);
            return rowsUpdated > 0;
        }
        return false;}

    @Override
    public boolean ValidateLogin(String username, String pass) throws SQLException, ClassNotFoundException {
        String sql = "SELECT Username, password FROM useraccount WHERE Username = ?";

        try (ResultSet resultSet = SQLUtil.execute(sql, username)) {
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                return storedPassword.equals(pass);
            }
        }
        return false;
    }

    @Override
    public boolean SaveUser(Admin admin) throws SQLException, ClassNotFoundException {
        String sql="Insert into useraccount(Username,nic_number,email,password) Values(?,?,?,?)";

        return SQLUtil.execute(sql,admin.getName(),admin.getNic(),admin.getEmail(),admin.getPass());
    }
}
