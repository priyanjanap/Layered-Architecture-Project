package Lk.ijse.Dress.Service.Custome.Impl;

import Lk.ijse.Dress.Service.Custome.EmployeeReportService;
import Lk.ijse.Dress.dao.Custom.EmployeeReportDAO;
import Lk.ijse.Dress.dao.DAOFactory;

import java.sql.SQLException;

public class EmployeeReportServiceImpl implements EmployeeReportService {
    EmployeeReportDAO employeeReportDAO = (  EmployeeReportDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EmployeeReport);

    @Override
    public String getNic(String nicNumber) throws SQLException, ClassNotFoundException {

        return employeeReportDAO.getEmpIdByNIC(nicNumber);
    }
}
