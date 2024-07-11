package Lk.ijse.Dress.Service.Custome;

import Lk.ijse.Dress.DTO.CustomerDTO;
import Lk.ijse.Dress.Service.AllService;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService extends AllService {
     CustomerDTO searchById(String id) throws Exception;

     boolean save(CustomerDTO customer) throws Exception;

    boolean update(CustomerDTO customer) throws Exception;
    boolean delete(String id) throws Exception;
    List<CustomerDTO> getAllCustomers() throws SQLException,ClassNotFoundException;

    List<String> getAllCustomerIds() throws SQLException,ClassNotFoundException;
    public int getCustomerCount() throws SQLException,ClassNotFoundException;

}
