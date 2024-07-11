package Lk.ijse.Dress.dao.Custom.impl;

import Lk.ijse.Dress.Entity.EmployeeAtt;
import Lk.ijse.Dress.dao.Custom.EmployeeAttDAO;

import java.sql.SQLException;
import java.util.List;

public class EmployeeAttDAOImpl implements EmployeeAttDAO {
    @Override
    public List<EmployeeAtt> getAll() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT employee_id, attendance_date, attendance_status FROM attendance";
        ArrayList<EmployeeAtt> attendanceList = new ArrayList<>();

        try (ResultSet resultSet = SQLUtil.execute(sql)) {
            while (resultSet.next()) {
                String employeeId = resultSet.getString("employee_id");

                Date attendanceDate = resultSet.getDate("attendance_date");
                String attendanceStatus = resultSet.getString("attendance_status");

                 EmployeeAtt attendance = new EmployeeAtt(null,employeeId, attendanceDate);
                attendanceList.add(attendance);
            }
        }

        return attendanceList;

         */
        return  null;
    }


    @Override
    public boolean add(EmployeeAtt entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(EmployeeAtt entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
