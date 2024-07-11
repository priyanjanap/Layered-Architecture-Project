package Lk.ijse.Dress.Service.Custome.Impl;

import Lk.ijse.Dress.DTO.MaterialDressDTO;
import Lk.ijse.Dress.Entity.MaterialDress;
import Lk.ijse.Dress.Service.Custome.MaterialDressService;
import Lk.ijse.Dress.dao.Custom.MaterialDressDAO;
import Lk.ijse.Dress.dao.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialDressServiceImpl implements MaterialDressService {
    MaterialDressDAO materialDressDAO= (MaterialDressDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MaterialDress);
    public ArrayList<MaterialDressDTO> loadAllMaterialDress() throws SQLException, ClassNotFoundException{
        ArrayList<MaterialDressDTO> arrayList=new ArrayList<>();
        for (MaterialDress materialDress: materialDressDAO.getAll()){
            arrayList.add(new MaterialDressDTO(materialDress.getId1(),materialDress.getQty(),materialDress.getId2(),materialDress.getPrice()));
        }return  arrayList;
    }

    @Override
    public boolean save(MaterialDressDTO materialDressDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(MaterialDressDTO materialDressDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
