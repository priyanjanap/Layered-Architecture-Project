package Lk.ijse.Dress.dao.Custom;

import Lk.ijse.Dress.Entity.Employee;
import Lk.ijse.Dress.dao.CrudDAO;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO extends CrudDAO <Employee>{
    public Employee search(String id) throws SQLException, ClassNotFoundException;
    public List<String> getALLEmployeeIDS() throws SQLException, ClassNotFoundException;
    public double getTotalSalary() throws SQLException,ClassNotFoundException;
    public int getEmployeeCount() throws SQLException,ClassNotFoundException;
    public Employee getEmployeeById(String employeeId) throws SQLException, ClassNotFoundException;

}
