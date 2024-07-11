package Lk.ijse.Dress.Service.Custome.Impl;

import Lk.ijse.Dress.Entity.PlaceOrder;
import Lk.ijse.Dress.Entity.PlaceRental;
import Lk.ijse.Dress.Service.Custome.TransactionService;
import Lk.ijse.Dress.dao.Custom.TransactionDAO;
import Lk.ijse.Dress.dao.DAOFactory;

import java.sql.SQLException;

public class TransactionServiceImpl implements TransactionService {
    TransactionDAO transactionDAO= (TransactionDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.trans);

    @Override
    public boolean placeOrder(PlaceOrder po) throws SQLException, ClassNotFoundException {
        return transactionDAO.placeOrder(po);
    }

    @Override
    public boolean placeRental(PlaceRental po) throws SQLException, ClassNotFoundException {
        return  transactionDAO.placeRental(po);
    }
}
