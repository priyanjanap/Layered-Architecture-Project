package Lk.ijse.Dress.Service.Custome.Impl;

import Lk.ijse.Dress.DTO.Material_supplierDTO;
import Lk.ijse.Dress.Entity.Material_supplier;
import Lk.ijse.Dress.Service.Custome.MaterialSupplierService;
import Lk.ijse.Dress.dao.Custom.MaterialSupplierDAO;
import Lk.ijse.Dress.dao.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialSupplierServiceImpl implements MaterialSupplierService {
    MaterialSupplierDAO materialSupplierDAO= (MaterialSupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MaterialSupplier);
    @Override
    public ArrayList<Material_supplierDTO> getAllMaterialSupplier() throws ClassNotFoundException, SQLException {
        ArrayList<Material_supplierDTO> arrayList= new ArrayList<>();
        for (Material_supplier materialSupplier: materialSupplierDAO.getAll()){
            arrayList.add(new Material_supplierDTO(materialSupplier.getSupplierId(),materialSupplier.getMaterialId(),materialSupplier.getMaterialName(),materialSupplier.getSupllierName(),materialSupplier.getQty(),materialSupplier.getPrice()));


        }return  arrayList;
    }
}
