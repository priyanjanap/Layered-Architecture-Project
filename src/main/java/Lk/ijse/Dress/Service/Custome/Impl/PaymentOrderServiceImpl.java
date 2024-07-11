package Lk.ijse.Dress.Service.Custome.Impl;

import Lk.ijse.Dress.DTO.PaymentOrderDTO;
import Lk.ijse.Dress.Entity.PaymentOrder;
import Lk.ijse.Dress.Model.Enum.PaymentType;
import Lk.ijse.Dress.Service.Custome.PaymentOrderService;
import Lk.ijse.Dress.dao.Custom.PaymentOrderDAO;
import Lk.ijse.Dress.dao.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentOrderServiceImpl implements PaymentOrderService {
    PaymentOrderDAO paymentOrderDAO= (PaymentOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Payment_Order);
    @Override
    public List<PaymentOrderDTO> getPaymentOrdersByNic(String nic) throws SQLException, ClassNotFoundException {
        ArrayList<PaymentOrderDTO>arrayList= new ArrayList<>();
        for (PaymentOrder paymentOrder: paymentOrderDAO.getPaymentOrdersByNic(nic)){
            arrayList.add(new PaymentOrderDTO(paymentOrder.getPayid(), paymentOrder.getCusid(), paymentOrder.getName(), paymentOrder.getTotal(), paymentOrder.getNic(), paymentOrder.getPaymentType(), paymentOrder.getAmount()));

        }return arrayList;
    }

    @Override
    public List<PaymentOrderDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentOrderDTO>arrayList= new ArrayList<>();
        for (PaymentOrder paymentOrder: paymentOrderDAO.getAll()){
            arrayList.add(new PaymentOrderDTO(paymentOrder.getPayid(), paymentOrder.getCusid(), paymentOrder.getName(), paymentOrder.getTotal(), paymentOrder.getNic(), paymentOrder.getPaymentType(), paymentOrder.getAmount()));

        }return arrayList;
    }

    @Override
    public boolean add(PaymentOrderDTO paymentOrder) throws SQLException, ClassNotFoundException {
        return paymentOrderDAO.add(new PaymentOrder(paymentOrder.getPayid(), paymentOrder.getCusid(), paymentOrder.getName(), paymentOrder.getTotal(), paymentOrder.getNic(), paymentOrder.getPaymentType(), paymentOrder.getAmount()));
    }

    @Override
    public boolean update(PaymentOrderDTO paymentOrder) throws SQLException, ClassNotFoundException {
        return paymentOrderDAO.update(new PaymentOrder(paymentOrder.getPayid(), paymentOrder.getCusid(), paymentOrder.getName(), paymentOrder.getTotal(), paymentOrder.getNic(), paymentOrder.getPaymentType(), paymentOrder.getAmount()));

    }
    @Override
    public boolean update2(String nic, double amount, PaymentType paymentType) throws SQLException, ClassNotFoundException{
        return paymentOrderDAO.update2(nic,amount,paymentType);
    }
}
