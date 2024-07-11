package Lk.ijse.Dress.Service.Custome.Impl;

import Lk.ijse.Dress.Service.Custome.QuearyService;
import Lk.ijse.Dress.dao.Custom.QueryDAO;
import Lk.ijse.Dress.dao.DAOFactory;

import java.sql.SQLException;

public class QuearyServiceImpl implements QuearyService {
    QueryDAO queryDAO= (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY_DAO);
    @Override
    public int countByNic(String nic) throws SQLException, ClassNotFoundException {
        return queryDAO.countByNic(nic);
    }
}
