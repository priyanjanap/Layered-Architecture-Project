package Lk.ijse.Dress.Service.Custome.Impl;

import Lk.ijse.Dress.DTO.SupplierPaymentDTO;
import Lk.ijse.Dress.Entity.SupplierPayment;
import Lk.ijse.Dress.Service.Custome.SupplierPaymentService;
import Lk.ijse.Dress.dao.Custom.SupplierPaymentDAO;
import Lk.ijse.Dress.dao.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierPaymentServiceImpl implements SupplierPaymentService {
    SupplierPaymentDAO supplierPaymentDAO= (SupplierPaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SupplierPayment);
    @Override
    public List<SupplierPaymentDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierPaymentDTO> arrayList=new ArrayList<>();
        for (SupplierPayment supplierPayment:supplierPaymentDAO.getAll()){
            arrayList.add(new SupplierPaymentDTO(supplierPayment.getId1(),supplierPayment.getId2(),supplierPayment.getName(),supplierPayment.getAmount(),supplierPayment.getDate()));
        }return  arrayList;
    }

    @Override
    public boolean add(SupplierPaymentDTO supplierPayment) throws SQLException, ClassNotFoundException {
        return supplierPaymentDAO.add(new SupplierPayment(supplierPayment.getId1(),supplierPayment.getId2(),supplierPayment.getName(),supplierPayment.getAmount(),supplierPayment.getDate()));
    }

    @Override
    public boolean update(SupplierPaymentDTO supplierPayment) throws SQLException, ClassNotFoundException {
        return supplierPaymentDAO.update(new SupplierPayment(supplierPayment.getId1(),supplierPayment.getId2(),supplierPayment.getName(),supplierPayment.getAmount(),supplierPayment.getDate()));

    }

    @Override
    public boolean delete(String payId) throws SQLException, ClassNotFoundException {
        return supplierPaymentDAO.delete(payId);
    }
}
