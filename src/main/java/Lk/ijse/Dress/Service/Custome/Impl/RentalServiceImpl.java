package Lk.ijse.Dress.Service.Custome.Impl;

import Lk.ijse.Dress.DTO.RentalDTO;
import Lk.ijse.Dress.Entity.Rental;
import Lk.ijse.Dress.Service.Custome.RentalService;
import Lk.ijse.Dress.dao.Custom.RentalDAO;
import Lk.ijse.Dress.dao.DAOFactory;

import java.sql.SQLException;

public class RentalServiceImpl implements RentalService {
    RentalDAO rentalDAO= (RentalDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Rental);
    @Override
    public boolean add(RentalDTO rental) throws SQLException, ClassNotFoundException {
        return rentalDAO.add(new Rental(rental.getRentalId(),rental.getCustomeid(),rental.getPaymentId(),rental.getStDate(),rental.getLastDate(),rental.getTimeDuration()));
    }

    @Override
    public String getAllRentalIds() throws SQLException, ClassNotFoundException {
        return rentalDAO.getAllRentalIds();
    }

    @Override
    public boolean delete(String rentalId) throws SQLException, ClassNotFoundException {
        return rentalDAO.delete(rentalId);
    }

    @Override
    public boolean update(RentalDTO rental) throws SQLException, ClassNotFoundException {
        return rentalDAO.update(new Rental(rental.getRentalId(),rental.getCustomeid(),rental.getPaymentId(),rental.getStDate(),rental.getLastDate(),rental.getTimeDuration()));

    }

    @Override
    public int getRentalCount() throws SQLException, ClassNotFoundException {
        return  rentalDAO.getRentalCount();
    }
}
