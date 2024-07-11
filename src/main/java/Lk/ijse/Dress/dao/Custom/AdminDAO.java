package Lk.ijse.Dress.dao.Custom;

import Lk.ijse.Dress.Entity.Admin;
import Lk.ijse.Dress.dao.SuperDAO;

import java.sql.SQLException;

public interface AdminDAO extends SuperDAO {
    boolean whereUserName(String username) throws SQLException,ClassNotFoundException;
    boolean changePassword(String username,String password) throws SQLException, ClassNotFoundException;
    boolean ValidateLogin(String username,String pass)throws  SQLException,ClassNotFoundException;

    boolean SaveUser(Admin admin) throws  SQLException,ClassNotFoundException;

}
