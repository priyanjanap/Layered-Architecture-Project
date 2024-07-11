package Lk.ijse.Dress.dao.Custom;

import Lk.ijse.Dress.Entity.MaterialType;
import Lk.ijse.Dress.Entity.OrderMaterial;
import Lk.ijse.Dress.dao.CrudDAO;

import java.sql.SQLException;
import java.util.List;

public interface MaterialDAO extends CrudDAO<MaterialType> {
    public MaterialType search(String id) throws SQLException, ClassNotFoundException;
    public List<String> getALLMaterialIDS() throws SQLException, ClassNotFoundException;
    public  boolean update1(List<OrderMaterial> odList) throws SQLException, ClassNotFoundException;

    boolean save2(List<Lk.ijse.Dress.Entity.OrderMaterial> odList) throws  SQLException, ClassNotFoundException;


}
