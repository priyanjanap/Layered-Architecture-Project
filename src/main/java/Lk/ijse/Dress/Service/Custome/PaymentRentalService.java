package Lk.ijse.Dress.Service.Custome;

import Lk.ijse.Dress.DTO.PaymentRentalDTO;
import Lk.ijse.Dress.Service.AllService;

import java.sql.SQLException;

public interface PaymentRentalService extends AllService {
    boolean add(PaymentRentalDTO paymentRentalDTO) throws SQLException, ClassNotFoundException;

}
