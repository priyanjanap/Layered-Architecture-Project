package Lk.ijse.Dress.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Material_supplier {
    private  String supplierId;
    private  String materialId;
    private  String materialName;
    private  String supllierName;
    private  int qty;
    private  double price;

}
