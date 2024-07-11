package Lk.ijse.Dress.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor
@Data
public class OrderPaymentDTO {
    private OrderDTO orderDTO;
    private PaymentDTO paymentDTO;
    private  String name;

}
