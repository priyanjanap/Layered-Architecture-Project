package Lk.ijse.Dress.dao.Custom.impl;

import Lk.ijse.Dress.Entity.PlaceOrder;
import Lk.ijse.Dress.Entity.PlaceRental;
import Lk.ijse.Dress.dao.Custom.*;
import Lk.ijse.Dress.dao.DAOFactory;
import Lk.ijse.Dress.db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionDAOImpl implements TransactionDAO {
    MaterialDAO materialDAO= (MaterialDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Material);
    RentalDAO rentalDAO= (RentalDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Rental);

    OrderDAO orderDAO= (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    PaymentDAO paymentDAO= (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Payment);
    @Override
    public boolean placeOrder(PlaceOrder po) throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            connection.setAutoCommit(false);

           /* boolean isOrderSaved = ordersRepo.save(po.getOrder(), connection);
            boolean isQtyUpdated = materialRepo.update(po.getOdlist(), connection);
            boolean isOrderDetailSaved = orderMaterialRepo.save(po.getOdlist(), connection);
            boolean isPaymentSaved = paymentRepo.save(po.getPayment(), connection);

            */
            boolean isOrderSaved= orderDAO.add(po.getOrder());
            boolean isQtyUpdated=materialDAO.update1(po.getOdlist());
            boolean isOrderDetailSaved=materialDAO.save2(po.getOdlist());
            boolean isPaymentSaved=paymentDAO.save(po.getPayment());
            if (isOrderSaved && isQtyUpdated && isOrderDetailSaved && isPaymentSaved) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean placeRental(PlaceRental po) throws SQLException, ClassNotFoundException {
        try {
            Connection  connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = rentalDAO.add(po.getRental());
            boolean isOrderDetailSaved = rentalDAO.save(po.getOdList());
            boolean isPaymentSaved = paymentDAO.save(po.getPayment());

            System.out.println("OrderSaved: "+isOrderSaved);
            System.out.println("OD saved : "+isOrderDetailSaved);
            System.out.println("payme save: "+isPaymentSaved);

            if (isOrderSaved && isOrderDetailSaved && isPaymentSaved) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                System.out.println("Transaction failed. Rolling back changes.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }
    }

