package Lk.ijse.Dress.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderMaterialDTO {
    private  String id1;
    private  String id2;
    private  int qty;
    private  double price;

}
