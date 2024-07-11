package Lk.ijse.Dress.dao.Custom.impl;

import Lk.ijse.Dress.Entity.MaterialDress;
import Lk.ijse.Dress.dao.Custom.MaterialDressDAO;
import Lk.ijse.Dress.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialDressDAOImpl implements MaterialDressDAO {
    @Override
    public List<MaterialDress> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM material_dress";
        List<MaterialDress> materialDresseslList = new ArrayList<>();

      try {
          ResultSet rst = SQLUtil.execute(sql);

          while (rst.next()) {

              MaterialDress materialModel = new MaterialDress(rst.getString(1),rst.getInt(2),rst.getString(3),rst.getDouble(4));
              materialDresseslList.add(materialModel);
          }

      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
        return materialDresseslList;
    }


    @Override
    public boolean add(MaterialDress entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(MaterialDress entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
