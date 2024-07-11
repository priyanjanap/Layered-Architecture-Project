package Lk.ijse.Dress.Service.Custome;

import Lk.ijse.Dress.DTO.AdminDTO;
import Lk.ijse.Dress.Service.AllService;

import java.sql.SQLException;

public interface AdminService extends AllService {
    boolean whereUserName(String username) throws SQLException,ClassNotFoundException;
    boolean changePassword(String username,String password) throws SQLException, ClassNotFoundException;
    boolean ValidateLogin(String username,String pass)throws  SQLException,ClassNotFoundException;

    boolean SaveUser(AdminDTO admin) throws  SQLException,ClassNotFoundException;

}
