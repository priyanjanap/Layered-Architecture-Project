package Lk.ijse.Dress.dao.Custom.impl;

import Lk.ijse.Dress.Entity.Employee;
import Lk.ijse.Dress.dao.Custom.EmployeeDAO;
import Lk.ijse.Dress.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public  Employee getEmployeeById(String employeeId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM employee WHERE Employee_ID = ?";
        ResultSet resultSet = SQLUtil.execute(sql, employeeId);

        if (resultSet.next()) {
            Employee employee = new Employee(resultSet.getString("Employee_ID"), resultSet.getString("Employee_name"), resultSet.getString("Employee_address"), resultSet.getInt("age"), resultSet.getDouble("salary"), resultSet.getString("gender"), resultSet.getDate("date_of_birth"), resultSet.getInt("phone_number"));

            return employee;
        } else {
            return null;
        }
    }

    @Override
    public List<Employee> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM employee";
        List<Employee> employeeList = new ArrayList<>();

        try (ResultSet resultSet = SQLUtil.execute(sql)) {
            while (resultSet.next()) {
                Employee employee = new Employee(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getDate(7), resultSet.getInt(8));
                employeeList.add(employee);
            }
        }

        return employeeList;

    }

    @Override
    public boolean add(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO employee (Employee_ID, Employee_name, Employee_address, age, salary, gender, date_of_birth, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        return SQLUtil.execute(sql, employee.getEmployeeId(), employee.getEmployeeName(), employee.getEmployeeAddress(), employee.getEmployeeAge(), employee.getSalary(), employee.getGender(), employee.getDateOfBirth(), employee.getContactNumber());
    }

    @Override
    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE employee SET ");
        List<Object> params = new ArrayList<>();

        if (employee.getEmployeeName() != null) {
            sqlBuilder.append("Employee_name = ?, ");
            params.add(employee.getEmployeeName());
        }
        if (employee.getEmployeeAddress() != null) {
            sqlBuilder.append("Employee_address = ?, ");
            params.add(employee.getEmployeeAddress());
        }
        if (employee.getContactNumber() != 0) {
            sqlBuilder.append("phone_number = ?, ");
            params.add(employee.getContactNumber());
        }
        if (employee.getSalary() != 0.0) {
            sqlBuilder.append("salary = ?, ");
            params.add(employee.getSalary());
        }

        sqlBuilder.setLength(sqlBuilder.length() - 2);
        sqlBuilder.append(" WHERE Employee_ID = ?");
        params.add(employee.getEmployeeId());

        String sql = sqlBuilder.toString();

        return SQLUtil.execute(sql, params.toArray());
    }

    @Override
    public boolean delete(String employeeId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM employee WHERE Employee_ID = ?";
        return SQLUtil.execute(sql, employeeId);
    }

    @Override
    public Employee search(String employeeId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM employee WHERE Employee_Id = ?";
        Employee employee = null;

        try (ResultSet resultSet = SQLUtil.execute(sql, employeeId)) {
            if (resultSet.next()) {
                employee = new Employee(resultSet.getString("Employee_Id"), resultSet.getString("Employee_name"), resultSet.getString("Employee_address"), resultSet.getInt("age"), resultSet.getDouble("salary"), resultSet.getString("gender"), resultSet.getDate("date_of_birth"), resultSet.getInt("phone_number"));
            }
        }

        return employee;
    }

    @Override
    public List<String> getALLEmployeeIDS() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Employee_ID FROM employee";

        ResultSet resultSet = SQLUtil.execute(sql);

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;

    }

    @Override
    public double getTotalSalary() throws SQLException, ClassNotFoundException {
        String sql = "SELECT SUM(salary) AS totalsalary FROM employee";
        double totalSalary = 0.0;
        try (
             ResultSet resultSet = SQLUtil.execute(sql)) {

            if (resultSet.next()) {
                totalSalary = resultSet.getDouble("totalsalary");
            }
        }
        return totalSalary;
    }

    @Override
    public int getEmployeeCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) AS EmployeeCount FROM employee";
        int employeeCount = 0;
        try (
             ResultSet resultSet = SQLUtil.execute(sql)) {
            if (resultSet.next()) {
                employeeCount = resultSet.getInt("EmployeeCount");
            }
        }
        return employeeCount;
    }

}

