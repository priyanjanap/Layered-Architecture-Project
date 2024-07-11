package Lk.ijse.Dress.Service.Custome.Impl;

import Lk.ijse.Dress.DTO.EmployeeDTO;
import Lk.ijse.Dress.Entity.Employee;
import Lk.ijse.Dress.Service.Custome.EmployeeService;
import Lk.ijse.Dress.dao.Custom.EmployeeDAO;
import Lk.ijse.Dress.dao.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Employee);

    @Override
    public List<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException {
          ArrayList<EmployeeDTO> arrayList= new ArrayList<>();
        for (Employee employeeDTO:employeeDAO.getAll()){
            arrayList.add(new EmployeeDTO(employeeDTO.getEmployeeId(),employeeDTO.getEmployeeName(),employeeDTO.getEmployeeAddress(),employeeDTO.getEmployeeAge(),employeeDTO.getSalary(),employeeDTO.getGender(),employeeDTO.getDateOfBirth(),employeeDTO.getContactNumber()));
        }
        return arrayList;
    }

    @Override
    public EmployeeDTO searchById(String id) throws Exception {
        Employee employee = employeeDAO.search(id);

        if (employee == null) {
            throw new Exception("Employee not found with ID: " + id);
        }

        EmployeeDTO employeeDTO = new EmployeeDTO(employee.getEmployeeId(), employee.getEmployeeName(), employee.getEmployeeAddress(),employee.getEmployeeAge(),employee.getSalary(),employee.getGender(),employee.getDateOfBirth(),employee.getContactNumber());

        return employeeDTO;    }

    @Override
    public boolean save(EmployeeDTO employeeDTO) throws Exception {
        return employeeDAO.add(new Employee(employeeDTO.getEmployeeId(),employeeDTO.getEmployeeName(),employeeDTO.getEmployeeAddress(),employeeDTO.getEmployeeAge(),employeeDTO.getSalary(),employeeDTO.getGender(),employeeDTO.getDateOfBirth(),employeeDTO.getContactNumber()));
    }

    @Override
    public boolean update(EmployeeDTO employeeDTO) throws Exception {
        return employeeDAO.update( new Employee(employeeDTO.getEmployeeId(),employeeDTO.getEmployeeName(),employeeDTO.getEmployeeAddress(),employeeDTO.getEmployeeAge(),employeeDTO.getSalary(),employeeDTO.getGender(),employeeDTO.getDateOfBirth(),employeeDTO.getContactNumber()));
    }

    @Override
    public List<String> getAllEmployeeIds() throws SQLException, ClassNotFoundException {
        return  employeeDAO.getALLEmployeeIDS();
    }

    @Override
    public double getTotalSalary() throws SQLException, ClassNotFoundException {
        return employeeDAO.getTotalSalary();
    }

    @Override
    public int getEmployeeCount() throws SQLException, ClassNotFoundException {
        return employeeDAO.getEmployeeCount();
    }

    @Override
    public EmployeeDTO getEmployeeById(String employeeId) throws SQLException, ClassNotFoundException {
Employee employee=employeeDAO.getEmployeeById(employeeId);
    if (employee==null){
        System.out.println("sss");
    }
    EmployeeDTO employeeDTO=new EmployeeDTO(employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeeAddress(),employee.getEmployeeAge(),employee.getSalary(),employee.getGender(),employee.getDateOfBirth(),employee.getContactNumber());
    return  employeeDTO;
    }
}
