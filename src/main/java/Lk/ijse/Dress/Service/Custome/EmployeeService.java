package Lk.ijse.Dress.Service.Custome;

import Lk.ijse.Dress.DTO.EmployeeDTO;
import Lk.ijse.Dress.Service.AllService;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService extends AllService {
    public List<EmployeeDTO> getAllEmployees() throws SQLException,ClassNotFoundException ;
    EmployeeDTO searchById(String id) throws Exception;

    boolean save(EmployeeDTO employee) throws Exception;

    boolean update(EmployeeDTO employee) throws Exception;

    List<String> getAllEmployeeIds() throws SQLException,ClassNotFoundException;
    public double getTotalSalary() throws SQLException,ClassNotFoundException;
    public int getEmployeeCount() throws SQLException, ClassNotFoundException;
    public EmployeeDTO getEmployeeById(String employeeId) throws SQLException, ClassNotFoundException;

}
