package Lk.ijse.Dress.dao.Custom.impl;

import Lk.ijse.Dress.Entity.Product;
import Lk.ijse.Dress.dao.Custom.ProductDAO;
import Lk.ijse.Dress.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public List<Product> getAll() throws SQLException, ClassNotFoundException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try (ResultSet resultSet = SQLUtil.execute(sql)) {
            while (resultSet.next()) {
                Product product=new Product(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
                products.add(product);
            }
        }

        return products;
    }

    @Override
    public boolean add(Product product) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO product (product_id, product_name, quantity, price, description, product_type, category) VALUES (?, ?, ?, ?, ?, ?, ?)";

        return SQLUtil.execute(sql, product.getId(), product.getName(), product.getQty(), product.getPrice(), product.getDescription(), product.getType(), product.getCategory());
    }

    @Override
    public boolean update(Product product) throws SQLException, ClassNotFoundException {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE product SET ");
        List<Object> params = new ArrayList<>();

        if (product.getName() != null) {
            sqlBuilder.append("product_name = ?, ");
            params.add(product.getName());
        }
        if (product.getQty() != 0) {
            sqlBuilder.append("quantity = ?, ");
            params.add(product.getQty());
        }
        if (product.getPrice() != 0.0) {
            sqlBuilder.append("price = ?, ");
            params.add(product.getPrice());
        }
        if (product.getDescription() != null) {
            sqlBuilder.append("description = ?, ");
            params.add(product.getDescription());
        }
        if (product.getType() != null) {
            sqlBuilder.append("product_type = ?, ");
            params.add(product.getType());
        }
        if (product.getCategory() != null) {
            sqlBuilder.append("category = ?, ");
            params.add(product.getCategory());
        }

        sqlBuilder.setLength(sqlBuilder.length() - 2);
        sqlBuilder.append(" WHERE product_id = ?");
        params.add(product.getId());

        String sql = sqlBuilder.toString();

        return SQLUtil.execute(sql, params.toArray());
    }

    @Override
    public boolean delete(String productId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM product WHERE product_id = ?";
        return SQLUtil.execute(sql, productId);
    }

    @Override
    public Product search(String productId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM product WHERE product_id = ?";

        ResultSet resultSet = SQLUtil.execute(sql, productId);
        resultSet.next();

        return new Product(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));


    }

}
