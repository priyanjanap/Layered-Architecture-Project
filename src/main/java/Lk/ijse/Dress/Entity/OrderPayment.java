package Lk.ijse.Dress.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor
@Data
public class OrderPayment {
    private Order order;
    private Payment payment;
    private  String name;

}
