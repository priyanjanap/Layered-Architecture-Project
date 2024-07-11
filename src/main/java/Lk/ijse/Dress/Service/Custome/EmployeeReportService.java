package Lk.ijse.Dress.Service.Custome;

import Lk.ijse.Dress.Service.AllService;

import java.sql.SQLException;

public interface EmployeeReportService extends AllService {

    String getNic(String nicNumber) throws SQLException,ClassNotFoundException;
}
