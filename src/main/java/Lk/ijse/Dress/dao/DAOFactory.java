package Lk.ijse.Dress.dao;

import Lk.ijse.Dress.dao.Custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER, Products, Supplier, ORDER, Payment_Order,  Dress, EmployeeATT, Employee,  EmployeeReport, Material, MaterialDress, MaterialSupplier, Payment, Rental, SupplierPayment, QUERY_DAO,Admin,Image,JoinQueayr,PaymentRental,trans;
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case Products:
                return new ProductDAOImpl();
            case Supplier:
                return new SupplierDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case Payment_Order:
                return new PaymentOrderDAOImpl();
            case Dress:
                return new DressDAOImpl();
            case EmployeeATT:
                return new EmployeeAttDAOImpl();
            case Employee:
                return new EmployeeDAOImpl();
            case Material:
                return new MaterialDAOImpl();
            case MaterialDress:
                return new MaterialDressDAOImpl();
            case MaterialSupplier:
                return new MaterialSupplierDAOImpl();
            case Payment:
                return new PaymentDAOImpl();
            case Rental:
                return new RentalDAOImpl();
            case SupplierPayment:
                return new SupplierPaymentDAOImpl();
            case QUERY_DAO:
                return new QueryDAOImpl();
            case EmployeeReport:
                return  new EmployeeReportDAOImpl();
            case Admin:
                return  new AdminDAOImpl();
            case Image:
                return  new ImageDAOImpl();
            case JoinQueayr:
                return  new JoinQuearyDAOImpl();
            case PaymentRental:
                return  new PaymentRentalDAOImpl();
            case trans:
                return  new TransactionDAOImpl();
            default:
                return null;
        }
    }


}
