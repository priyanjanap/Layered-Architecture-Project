package Lk.ijse.Dress.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDiscount {
    private  String payid;
    private  String cusid;
    private  String name;
    private  double total;
  private Date date;
  private  String id;


}
