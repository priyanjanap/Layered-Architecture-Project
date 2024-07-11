package Lk.ijse.Dress.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaceRentalDTO {
    private RentalDTO rentalDTO;
    private List<RentalDress> odList;
    private PaymentDTO paymentDTO;
}
