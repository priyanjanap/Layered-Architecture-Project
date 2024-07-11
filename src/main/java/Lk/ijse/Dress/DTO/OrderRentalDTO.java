package Lk.ijse.Dress.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor@Data@AllArgsConstructor
public class OrderRentalDTO {
    private RentalDTO rentalDTO;
    private PaymentDTO paymentDTO;
    private  String name;
}
