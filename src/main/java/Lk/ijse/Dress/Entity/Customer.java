package Lk.ijse.Dress.Entity;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Customer {
    private String Customer_Id;
    private String Customer_name;
    private String Customer_Address;
    private int Customer_contact_Number;

    private String email;

    public Customer(String customer_Id, String customer_name, String customer_Address, int customer_contact_Number, String email) {
        Customer_Id = customer_Id;
        Customer_name = customer_name;
        Customer_Address = customer_Address;
        Customer_contact_Number = customer_contact_Number;
        this.email = email;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Customer_Id='" + Customer_Id + '\'' +
                ", Customer_name='" + Customer_name + '\'' +
                ", Customer_Address='" + Customer_Address + '\'' +
                ", Customer_contact_Number=" + Customer_contact_Number +
                ", email='" + email + '\'' +
                '}';
    }

}
