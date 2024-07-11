package Lk.ijse.Dress.Service.Custome.Impl;

import Lk.ijse.Dress.DTO.CustomerDTO;
import Lk.ijse.Dress.Entity.Customer;
import Lk.ijse.Dress.Service.Custome.CustomerService;
import Lk.ijse.Dress.dao.Custom.CustomerDAO;
import Lk.ijse.Dress.dao.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
@Override
    public CustomerDTO searchById(String id) throws Exception {
        Customer customer=customerDAO.search(id);
        if (customer==null){
            System.out.println("cant find Id"+id);
        }
            CustomerDTO customerDTO=new CustomerDTO(customer.getCustomer_Id(),customer.getCustomer_name(),customer.getCustomer_Address(),customer.getCustomer_contact_Number(),customer.getEmail());
        return customerDTO;

    }

    @Override
    public boolean save(CustomerDTO customer) throws Exception {
        return customerDAO.add(new Customer(customer.getCustomer_Id(),customer.getCustomer_name(),customer.getCustomer_Address(), customer.getCustomer_contact_Number(), customer.getEmail()));
    }

    @Override
    public boolean update(CustomerDTO customer) throws Exception {
        return customerDAO.update(new Customer(customer.getCustomer_Id(),customer.getCustomer_name(),customer.getCustomer_Address(), customer.getCustomer_contact_Number(), customer.getEmail()));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return customerDAO.delete(id);
    }


    @Override
    public List<CustomerDTO> getAllCustomers() throws SQLException,ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers= new ArrayList<>();
       // List<Customer> all = customerDAO.getAll();
        for (Customer customer :customerDAO.getAll()) {
            allCustomers.add(new CustomerDTO(customer.getCustomer_Id(),customer.getCustomer_name(),customer.getCustomer_Address(), customer.getCustomer_contact_Number(), customer.getEmail()));
        }
        return allCustomers;

    }

    @Override
    public List<String> getAllCustomerIds() throws SQLException ,ClassNotFoundException {
        return customerDAO.getALLCustomerIDS();
    }

    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {
        return  customerDAO.getCustomerCount();
    }
}
