package Lk.ijse.Dress.dao.Custom.impl;

import Lk.ijse.Dress.Entity.MaterialType;
import Lk.ijse.Dress.Entity.OrderMaterial;
import Lk.ijse.Dress.dao.Custom.MaterialDAO;
import Lk.ijse.Dress.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAOImpl implements MaterialDAO {
    private static boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE material SET quantity = quantity - ? WHERE Material_Id = ?";
        return SQLUtil.execute(sql, qty, itemCode);
    }

    @Override
    public boolean update1(List<Lk.ijse.Dress.Entity.OrderMaterial> odList) throws SQLException, ClassNotFoundException {
        for (OrderMaterial od : odList) {
            boolean isUpdateQty = updateQty(od.getId2(), od.getQty());
            if (!isUpdateQty) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean save2(List<Lk.ijse.Dress.Entity.OrderMaterial> odList) throws SQLException, ClassNotFoundException {
        for (Lk.ijse.Dress.Entity.OrderMaterial od : odList) {
            boolean isSaved = save1(od);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }
    private static boolean save(Lk.ijse.Dress.Entity.OrderMaterial od) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orders VALUES(?, ?, ?,?)";
        return  SQLUtil.execute(sql,od.getId1(),od.getId2(),od
                .getQty(),od.getPrice());
    }
    private static boolean save1(Lk.ijse.Dress.Entity.OrderMaterial od) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO ordermaterial VALUES(?, ?, ?, ?)";
        return SQLUtil.execute(sql,od.getId1(),od.getId2(),od.getQty(),od.getPrice());
    }

    @Override
    public List<MaterialType> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM material";
        List<MaterialType> materialList = new ArrayList<>();

        ResultSet rst = SQLUtil.execute(sql);
        while (rst.next()) {


            MaterialType materialModel = new MaterialType(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getDouble(4)

            );

            materialList.add(materialModel);
        }

        return materialList;

    }

    @Override
    public boolean add(MaterialType materialModel) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO material VALUES (?, ?, ?, ?)";
        return SQLUtil.execute(sql, materialModel.getMaterialId(), materialModel.getMaterialName(), materialModel.getQty(), materialModel.getPrice());
    }

    @Override
    public boolean update(MaterialType material) throws SQLException, ClassNotFoundException {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE material SET ");
        List<Object> params = new ArrayList<>();

        if (material.getMaterialName() != null) {
            sqlBuilder.append("Material_name = ?, ");
            params.add(material.getMaterialName());
        }
        if (material.getQty() != 0) {
            sqlBuilder.append("quantity = ?, ");
            params.add(material.getQty());
        }
        if (material.getPrice() != 0) {
            sqlBuilder.append("price = ?, ");
            params.add(material.getPrice());
        }

        sqlBuilder.setLength(sqlBuilder.length() - 2);
        sqlBuilder.append(" WHERE Material_Id = ?");
        params.add(material.getMaterialId());

        String sql = sqlBuilder.toString();

        return SQLUtil.execute(sql, params.toArray());
    }

    @Override
    public boolean delete(String materialId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM material WHERE Material_Id = ?";
        return SQLUtil.execute(sql, materialId);
    }

    @Override
    public MaterialType search(String materialId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM material WHERE Material_Id = ?";
        ResultSet resultSet = SQLUtil.execute(sql, materialId);

        if (resultSet.next()) {


            return new MaterialType(materialId, resultSet.getString(2), resultSet.getInt(3), resultSet.getDouble(4));
        }

        return null;
    }

    @Override
    public List<String> getALLMaterialIDS() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Material_Id FROM material";
        ResultSet resultSet = SQLUtil.execute(sql);

        List<String> materialIds = new ArrayList<>();
        while (resultSet.next()) {
            String materialId = resultSet.getString("Material_Id");
            materialIds.add(materialId);
        }

        return materialIds;
    }
}
