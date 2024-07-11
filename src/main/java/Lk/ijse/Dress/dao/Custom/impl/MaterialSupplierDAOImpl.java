package Lk.ijse.Dress.dao.Custom.impl;

import Lk.ijse.Dress.Entity.Material_supplier;
import Lk.ijse.Dress.dao.Custom.MaterialSupplierDAO;
import Lk.ijse.Dress.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialSupplierDAOImpl implements MaterialSupplierDAO {
    @Override
    public List<Material_supplier> getAll() throws SQLException, ClassNotFoundException {
        List<Material_supplier> materialSupplierList = new ArrayList<>();
        String sql = "SELECT * from  material_supplier";


        try (ResultSet resultSet = SQLUtil.execute(sql)) {

            while (resultSet.next()) {

                Material_supplier materialSupplier = new Material_supplier(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getDouble(6));
                materialSupplierList.add(materialSupplier);
            }
        }

        return materialSupplierList;

    }

    @Override
    public boolean add(Material_supplier entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Material_supplier entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
