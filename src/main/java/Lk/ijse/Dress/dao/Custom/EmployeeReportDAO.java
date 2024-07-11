package Lk.ijse.Dress.dao.Custom;

import Lk.ijse.Dress.Entity.EmployeeReport;
import Lk.ijse.Dress.dao.CrudDAO;

import java.sql.SQLException;

public interface EmployeeReportDAO  extends CrudDAO<EmployeeReport> {
     String getEmpIdByNIC(String nicNumber) throws SQLException, ClassNotFoundException;
}
