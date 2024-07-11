package Lk.ijse.Dress.Service.Custome;

import Lk.ijse.Dress.DTO.Material_supplierDTO;
import Lk.ijse.Dress.Service.AllService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MaterialSupplierService extends AllService {

    ArrayList<Material_supplierDTO> getAllMaterialSupplier()throws  ClassNotFoundException, SQLException;
}
