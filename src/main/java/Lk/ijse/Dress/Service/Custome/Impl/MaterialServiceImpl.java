package Lk.ijse.Dress.Service.Custome.Impl;

import Lk.ijse.Dress.DTO.MaterialTypeDTO;
import Lk.ijse.Dress.DTO.OrderMaterialDTO;
import Lk.ijse.Dress.Entity.MaterialType;
import Lk.ijse.Dress.Service.Custome.MaterialService;
import Lk.ijse.Dress.dao.Custom.MaterialDAO;
import Lk.ijse.Dress.dao.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialServiceImpl implements MaterialService {

    MaterialDAO materialDAO = (MaterialDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Material);

    @Override
    public ArrayList<MaterialTypeDTO> loadAllMaterial1() throws SQLException, ClassNotFoundException {
        ArrayList<MaterialTypeDTO> arrayList = new ArrayList<>();

        for (MaterialType e : materialDAO.getAll()) {
            arrayList.add(new MaterialTypeDTO(e.getMaterialId(), e.getMaterialName(), e.getQty(), e.getPrice()));

        }
        return arrayList;
    }

    @Override
    public ArrayList<MaterialTypeDTO> loadAllMaterial2(String id) throws SQLException, ClassNotFoundException {
        ArrayList<MaterialTypeDTO> arrayList = new ArrayList<>();

        for (MaterialType e : materialDAO.getAll()) {
            arrayList.add(new MaterialTypeDTO(e.getMaterialId(), e.getMaterialName(), e.getQty(), e.getPrice()));

        }
        return arrayList;

    }

    @Override
    public boolean save(MaterialTypeDTO materialTypeDTO) throws SQLException, ClassNotFoundException {
        return materialDAO.add(new MaterialType(materialTypeDTO.getMaterialId(), materialTypeDTO.getMaterialName(), materialTypeDTO.getQty(),materialTypeDTO.getPrice()));
    }

    @Override
    public boolean update1(MaterialTypeDTO materialTypeDTO) throws SQLException, ClassNotFoundException {
        return materialDAO.update(new MaterialType(materialTypeDTO.getMaterialId(), materialTypeDTO.getMaterialName(), materialTypeDTO.getQty(),materialTypeDTO.getPrice()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return materialDAO.delete(id);
    }

    @Override
    public MaterialTypeDTO search(String id) throws SQLException, ClassNotFoundException {
        MaterialType materialType= materialDAO.search(id);
        if (materialType==null){
            System.out.println("cant find id"+id);

        }
        MaterialTypeDTO materialTypeDTO=new MaterialTypeDTO(materialType.getMaterialId(), materialType.getMaterialName(), materialType.getQty(),materialType.getPrice());
        return  materialTypeDTO;
    }

    @Override
    public boolean update2(List<OrderMaterialDTO> odList) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<String> loadAllMaterialIds() throws SQLException, ClassNotFoundException {
        return  materialDAO.getALLMaterialIDS();
    }
}
