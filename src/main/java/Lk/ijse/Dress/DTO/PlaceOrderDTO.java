package Lk.ijse.Dress.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaceOrderDTO {
    private OrderDTO orderDTO;
    private List<OrderMaterialDTO> odlist;
    private PaymentDTO paymentDTO;
}
