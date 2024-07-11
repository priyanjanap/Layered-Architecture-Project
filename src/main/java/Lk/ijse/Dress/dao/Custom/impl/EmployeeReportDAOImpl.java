package Lk.ijse.Dress.dao.Custom.impl;

import Lk.ijse.Dress.Entity.EmployeeReport;
import Lk.ijse.Dress.dao.Custom.EmployeeReportDAO;
import Lk.ijse.Dress.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeReportDAOImpl implements EmployeeReportDAO {
    @Override
    public String getEmpIdByNIC(String nicNumber) throws SQLException, ClassNotFoundException {
        String empId = null;
        String query = "SELECT empId FROM qr_codes WHERE nic_number = ?";


            ResultSet rs = SQLUtil.execute(query,nicNumber);

            if (rs.next()) {
                empId = rs.getString("empId");
            }


        return empId;
    }

    @Override
    public List<EmployeeReport> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(EmployeeReport entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(EmployeeReport entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}

