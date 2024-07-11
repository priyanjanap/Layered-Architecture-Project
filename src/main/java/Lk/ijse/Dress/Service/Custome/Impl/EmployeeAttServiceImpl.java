package Lk.ijse.Dress.Service.Custome.Impl;

import Lk.ijse.Dress.DTO.EmployeeAttDTO;
import Lk.ijse.Dress.Entity.EmployeeAtt;
import Lk.ijse.Dress.Service.Custome.EmployeeAttService;
import Lk.ijse.Dress.dao.Custom.EmployeeAttDAO;
import Lk.ijse.Dress.dao.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAttServiceImpl implements EmployeeAttService {
EmployeeAttDAO employeeAttDAO= (EmployeeAttDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Employee);

    @Override
    public List<EmployeeAttDTO> getAllEmployeeAtt() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeAttDTO> allEmployee= new ArrayList<>();
      //  List<EmployeeAtt> all = employeeAttDAO.getAll();
        for (EmployeeAtt c :  employeeAttDAO.getAll()) {
            allEmployee.add(new EmployeeAttDTO(c.getId1(), c.getId2(),c.getDate(),c.getStatus(),c.getStatus2()) );
        }
        return allEmployee;

    }
}
