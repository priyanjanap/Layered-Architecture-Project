package Lk.ijse.Dress.DTO;


import Lk.ijse.Dress.Model.Enum.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data@NoArgsConstructor
public class PaymentRentalDTO {
private String payid;
private  String cusid;
private  String cusname;
private double total;
private String nic;
private PaymentType paymentType;
private  double amount;


}
