package Lk.ijse.Dress.Service.Custome.Impl;

import Lk.ijse.Dress.DTO.AdminDTO;
import Lk.ijse.Dress.Entity.Admin;
import Lk.ijse.Dress.Service.Custome.AdminService;
import Lk.ijse.Dress.dao.Custom.AdminDAO;
import Lk.ijse.Dress.dao.DAOFactory;

import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {
    AdminDAO adminDAO= (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Admin);
    @Override
    public boolean whereUserName(String username) throws SQLException, ClassNotFoundException {
        return adminDAO.whereUserName(username);
    }

    @Override
    public boolean changePassword(String username, String password) throws SQLException, ClassNotFoundException {
        return adminDAO.changePassword(username,password);
    }

    @Override
    public boolean ValidateLogin(String username, String pass) throws SQLException, ClassNotFoundException {
        return adminDAO.ValidateLogin(username,pass);
    }

    @Override
    public boolean SaveUser(AdminDTO admin) throws SQLException, ClassNotFoundException {
        return adminDAO.SaveUser(new Admin(admin.getName(), admin.getNic(),  admin.getEmail(),admin.getPass()));
    }
}
