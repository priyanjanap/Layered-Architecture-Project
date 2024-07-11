package Lk.ijse.Dress.Service;

import Lk.ijse.Dress.Service.Custome.Impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {
    }

    public static ServiceFactory getServiceFactory() {
        return (serviceFactory == null) ? serviceFactory = new ServiceFactory() : serviceFactory;
    }

    public AllService getDAO(ServiceType types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerServiceImpl();
            case Dress:
                return new DressServiceImpl();
            case EmployeeATT:
                return new EmployeeAttServiceImpl();
            case Employee:
                return new EmployeeServiceImpl();
            case EmployeeReport:
                return new EmployeeReportServiceImpl();
            case Material:
                return new MaterialServiceImpl();
            case MaterialDress:
                return new MaterialDressServiceImpl();
            case MaterialSupplier:
                return new MaterialSupplierServiceImpl();
            case ORDER:
                return new OrderServiceImpl();
            case Payment:
                return new PaymentServiceImpl();
            case Payment_Order:
                return new PaymentOrderServiceImpl();

            case Products:
                return  new ProductServiceImpl();
            case QUERY_DAO:
                return  new QuearyServiceImpl();
            case Rental:
                return  new RentalServiceImpl();
            case Supplier:
                return new SupplierServiceImpl();
            case SupplierPayment:
                return new SupplierPaymentServiceImpl();
            case Admin:
                return new AdminServiceImpl();
            case Image:
                return  new ImageServiceImpl();

            case JoinQueay:
                return new JoinQuearyServiceImpl();
            case PRntal:
                return  new PaymentRentalServiceImpl();
            case Trnas:
                return  new TransactionServiceImpl();



            default:
                return null;
        }
    }

    public enum ServiceType {
        CUSTOMER, Products, Supplier, ORDER, Payment_Order, Dress, EmployeeATT, Employee, EmployeeReport, Material, MaterialDress, MaterialSupplier, Payment, Rental, SupplierPayment, QUERY_DAO,Admin,Image,JoinQueay,PRntal,Trnas
    }


}
