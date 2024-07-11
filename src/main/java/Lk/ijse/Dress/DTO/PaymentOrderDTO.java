package Lk.ijse.Dress.DTO;

import Lk.ijse.Dress.Model.Enum.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOrderDTO {
    private  String payid;
    private  String cusid;
    private  String name;
    private  double total;
    private  String nic;
    private PaymentType paymentType;
    private  double amount;

}
