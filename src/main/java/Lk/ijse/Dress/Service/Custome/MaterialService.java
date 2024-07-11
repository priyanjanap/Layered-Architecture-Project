package Lk.ijse.Dress.Service.Custome;

import Lk.ijse.Dress.DTO.MaterialTypeDTO;
import Lk.ijse.Dress.DTO.OrderMaterialDTO;
import Lk.ijse.Dress.Service.AllService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MaterialService extends AllService {
    ArrayList<MaterialTypeDTO> loadAllMaterial1() throws SQLException, ClassNotFoundException;

    ArrayList<MaterialTypeDTO> loadAllMaterial2(String id) throws SQLException, ClassNotFoundException;

    boolean save(MaterialTypeDTO materialTypeDTO) throws SQLException, ClassNotFoundException;

    boolean update1(MaterialTypeDTO materialTypeDTO) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    MaterialTypeDTO search(String id) throws SQLException, ClassNotFoundException;

    boolean update2(List<OrderMaterialDTO> odList) throws SQLException, ClassNotFoundException;

    boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;


    List<String> loadAllMaterialIds() throws SQLException, ClassNotFoundException;


}
