package Lk.ijse.Dress.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Supplier {

    private  String SupplierId;
    private  String SupplierName;
    private  String SupplierAddress;
    private  int ContactNumber;

}
