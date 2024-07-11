package Lk.ijse.Dress.Service.Custome.Impl;

import Lk.ijse.Dress.DTO.PaymentDTO;
import Lk.ijse.Dress.Entity.Payment;
import Lk.ijse.Dress.Service.Custome.PaymentService;
import Lk.ijse.Dress.dao.Custom.PaymentDAO;
import Lk.ijse.Dress.dao.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    PaymentDAO paymentDAO= (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Payment);
    @Override
    public boolean save(List<Payment> odList) throws SQLException, ClassNotFoundException {
        List<PaymentDTO> paymentDTOS=new ArrayList<>();
        for (Payment od : odList) {
            boolean isSaved = paymentDAO.save(od);
            if (!isSaved) {
                return false;
            }
        }
        return true;

    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Payment_Id FROM payment ORDER BY Payment_Id DESC LIMIT 1";
        return  paymentDAO.getPaymentId();

    }
}
