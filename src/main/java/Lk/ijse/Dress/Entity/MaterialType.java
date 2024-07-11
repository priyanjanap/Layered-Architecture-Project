package Lk.ijse.Dress.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaterialType {

    private  String MaterialId;
    private  String MaterialName;
    private  int Qty;
    private  double price;

}
