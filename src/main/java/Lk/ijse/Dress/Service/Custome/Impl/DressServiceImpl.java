package Lk.ijse.Dress.Service.Custome.Impl;

import Lk.ijse.Dress.DTO.DressDTO;
import Lk.ijse.Dress.Entity.Dress;
import Lk.ijse.Dress.Service.Custome.DressService;
import Lk.ijse.Dress.dao.Custom.DressDAO;
import Lk.ijse.Dress.dao.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DressServiceImpl implements DressService {

    DressDAO dressDAO = (DressDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Dress);

    @Override
    public List<String> getAllDressIds() throws SQLException, ClassNotFoundException {
        return dressDAO.getALLDressIDS();
    }

    @Override
    public List<DressDTO> getAllDress(String id) throws SQLException, ClassNotFoundException {
        ArrayList<DressDTO> arrayList = new ArrayList();

        for (Dress d : dressDAO.getAll()) {
            arrayList.add(new DressDTO(d.getId(), d.getName(), d.getDays(), d.getPircePerDate(), d.getStdate(), d.getLastDate()));
        }
        return arrayList;
    }

    @Override
    public boolean update(DressDTO d) throws Exception {
        return dressDAO.update(new Dress(d.getId(), d.getName(), d.getDays(), d.getPircePerDate(), d.getStdate(), d.getLastDate()));
    }
}
