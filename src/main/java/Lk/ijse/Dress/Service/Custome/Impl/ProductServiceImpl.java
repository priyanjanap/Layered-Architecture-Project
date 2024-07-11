package Lk.ijse.Dress.Service.Custome.Impl;

import Lk.ijse.Dress.DTO.ProductDTO;
import Lk.ijse.Dress.Entity.Product;
import Lk.ijse.Dress.Service.Custome.ProductService;
import Lk.ijse.Dress.dao.Custom.ProductDAO;
import Lk.ijse.Dress.dao.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductDAO productDAO= (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Products);
    @Override
    public List<ProductDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ProductDTO> arrayList= new ArrayList<>();
        for (Product p:productDAO.getAll()){
            arrayList.add(new ProductDTO(p.getId(),p.getName(),p.getQty(),p.getPrice(),p.getDescription(),p.getType(),p.getCategory()));

        }return  arrayList;
    }

    @Override
    public boolean add(ProductDTO p) throws SQLException, ClassNotFoundException {
        return productDAO.add(new Product(p.getId(),p.getName(),p.getQty(),p.getPrice(),p.getDescription(),p.getType(),p.getCategory()));
    }

    @Override
    public boolean update(ProductDTO p) throws SQLException, ClassNotFoundException {
        return productDAO.update(new Product(p.getId(),p.getName(),p.getQty(),p.getPrice(),p.getDescription(),p.getType(),p.getCategory()));

    }

    @Override
    public boolean delete(String productId) throws SQLException, ClassNotFoundException {
        return productDAO.delete(productId);
    }

    @Override
    public ProductDTO search(String productId) throws SQLException, ClassNotFoundException {
        Product product=productDAO.search(productId);
        if (product==null){
            System.out.println("ssdsd");

        }ProductDTO productDTO=new ProductDTO(product.getId(),product.getName(),product.getQty(),product.getPrice(),product.getDescription(),product.getType(),product.getCategory());
    return  productDTO;}
}
