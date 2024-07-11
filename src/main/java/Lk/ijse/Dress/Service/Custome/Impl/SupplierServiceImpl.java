package Lk.ijse.Dress.Service.Custome.Impl;

import Lk.ijse.Dress.DTO.SupplierDTO;
import Lk.ijse.Dress.Entity.Supplier;
import Lk.ijse.Dress.Service.Custome.SupplierService;
import Lk.ijse.Dress.dao.Custom.SupplierDAO;
import Lk.ijse.Dress.dao.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierServiceImpl implements SupplierService {
    SupplierDAO supplierDAO= (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Supplier);
    @Override
    public SupplierDTO getById(String supplierId) throws SQLException, ClassNotFoundException {
       Supplier supplier= supplierDAO.search(supplierId);
       if (supplier==null)
       {
           System.out.println("supplier id cant find"+supplierId);

       }   SupplierDTO supplierDTO= new SupplierDTO(supplier.getSupplierId(),supplier.getSupplierName(),supplier.getSupplierAddress(),supplier.getContactNumber());
        return supplierDTO;}


    @Override
    public List<SupplierDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList <SupplierDTO > arrayList=new ArrayList<>();
        for (Supplier supplier: supplierDAO.getAll()){
            arrayList.add(new SupplierDTO(supplier.getSupplierId(),supplier.getSupplierName(),supplier.getSupplierAddress(),supplier.getContactNumber()));

        }   return arrayList;
    }

    @Override
    public boolean add(SupplierDTO supplier) throws SQLException, ClassNotFoundException {
        return supplierDAO.add(new Supplier(supplier.getSupplierId(),supplier.getSupplierName(),supplier.getSupplierAddress(),supplier.getContactNumber()));
    }

    @Override
    public boolean update(SupplierDTO supplier) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supplier.getSupplierId(),supplier.getSupplierName(),supplier.getSupplierAddress(),supplier.getContactNumber()));

    }

    @Override
    public boolean delete(String supplierId) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(supplierId);
    }

    @Override
    public SupplierDTO search(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier=supplierDAO.search(id);
        if (supplier==null){
            System.out.println("supplier id cant find"+id);

        }   SupplierDTO supplierDTO= new SupplierDTO(supplier.getSupplierId(),supplier.getSupplierName(),supplier.getSupplierAddress(),supplier.getContactNumber());
    return supplierDTO;}

    @Override
    public List<String> getALLSupplierIDS() throws SQLException, ClassNotFoundException {
        return supplierDAO.getALLSupplierIDS();
    }

    @Override
    public int getSupplierCount() throws SQLException, ClassNotFoundException {
        try {
            return supplierDAO.getSupplierCount();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
