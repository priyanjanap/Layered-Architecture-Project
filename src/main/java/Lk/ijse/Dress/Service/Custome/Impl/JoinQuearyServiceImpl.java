package Lk.ijse.Dress.Service.Custome.Impl;

import Lk.ijse.Dress.Entity.RentalTable;
import Lk.ijse.Dress.Service.Custome.JoinQuearyService;
import Lk.ijse.Dress.dao.Custom.JoinQuearyDAO;
import Lk.ijse.Dress.dao.DAOFactory;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class JoinQuearyServiceImpl implements JoinQuearyService {
    JoinQuearyDAO joinQuearyDAO = (JoinQuearyDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.JoinQueayr);

    @Override
    public ObservableList<RentalTable> getRentals() throws SQLException, ClassNotFoundException {
        try {
            return joinQuearyDAO.loadRentalAndOrdersData();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}