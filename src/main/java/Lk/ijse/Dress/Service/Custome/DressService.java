package Lk.ijse.Dress.Service.Custome;

import Lk.ijse.Dress.DTO.DressDTO;
import Lk.ijse.Dress.Service.AllService;

import java.sql.SQLException;
import java.util.List;

public interface DressService extends AllService {
     List<String> getAllDressIds() throws SQLException,ClassNotFoundException ;
    List<DressDTO> getAllDress(String id) throws SQLException,ClassNotFoundException;

    boolean update(DressDTO d) throws Exception;

    }
