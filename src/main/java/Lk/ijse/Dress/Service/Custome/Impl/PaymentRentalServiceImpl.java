package Lk.ijse.Dress.Service.Custome.Impl;

import Lk.ijse.Dress.DTO.PaymentRentalDTO;
import Lk.ijse.Dress.Entity.PaymentRental;
import Lk.ijse.Dress.Service.Custome.PaymentRentalService;
import Lk.ijse.Dress.dao.Custom.PaymentRentalDAO;
import Lk.ijse.Dress.dao.DAOFactory;

import java.sql.SQLException;

public class PaymentRentalServiceImpl implements PaymentRentalService {
    PaymentRentalDAO paymentRentalDAO= (PaymentRentalDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PaymentRental);
    @Override
    public boolean add(PaymentRentalDTO paymentOrder) throws SQLException, ClassNotFoundException {
        return paymentRentalDAO.add(new PaymentRental(paymentOrder.getPayid(), paymentOrder.getCusid(), paymentOrder.getCusname(), paymentOrder.getTotal(), paymentOrder.getNic(), paymentOrder.getPaymentType(), paymentOrder.getAmount()));

    }
}
