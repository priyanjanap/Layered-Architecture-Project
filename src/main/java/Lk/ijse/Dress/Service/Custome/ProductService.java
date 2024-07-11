package Lk.ijse.Dress.Service.Custome;

import Lk.ijse.Dress.DTO.ProductDTO;
import Lk.ijse.Dress.Service.AllService;

import java.sql.SQLException;
import java.util.List;

public interface ProductService extends AllService {
    List<ProductDTO> getAll() throws SQLException, ClassNotFoundException;
    boolean add(ProductDTO product) throws SQLException, ClassNotFoundException;
    boolean update(ProductDTO product) throws SQLException, ClassNotFoundException;
    boolean delete(String productId) throws SQLException, ClassNotFoundException;
    ProductDTO search(String productId) throws SQLException, ClassNotFoundException;
}
