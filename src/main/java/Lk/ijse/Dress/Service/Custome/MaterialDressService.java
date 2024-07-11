package Lk.ijse.Dress.Service.Custome;

import Lk.ijse.Dress.DTO.MaterialDressDTO;
import Lk.ijse.Dress.Service.AllService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MaterialDressService extends AllService {
    ArrayList<MaterialDressDTO> loadAllMaterialDress() throws SQLException, ClassNotFoundException;

   boolean save(MaterialDressDTO materialDressDTO)throws SQLException, ClassNotFoundException;


    boolean update(MaterialDressDTO materialDressDTO)throws SQLException, ClassNotFoundException;

    boolean delete(String id)throws SQLException, ClassNotFoundException;

}
