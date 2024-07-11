package Lk.ijse.Dress.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaterialTypeDTO {

    private  String MaterialId;
    private  String MaterialName;
    private  int Qty;
    private  double price;

}
