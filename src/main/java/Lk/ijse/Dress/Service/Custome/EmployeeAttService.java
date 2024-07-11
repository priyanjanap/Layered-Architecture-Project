package Lk.ijse.Dress.Service.Custome;

import Lk.ijse.Dress.DTO.EmployeeAttDTO;
import Lk.ijse.Dress.Service.AllService;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeAttService extends AllService {
    List<EmployeeAttDTO> getAllEmployeeAtt() throws SQLException,ClassNotFoundException;
}
