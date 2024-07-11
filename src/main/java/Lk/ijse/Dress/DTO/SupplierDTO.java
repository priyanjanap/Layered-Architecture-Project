package Lk.ijse.Dress.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SupplierDTO {

    private  String SupplierId;
    private  String SupplierName;
    private  String SupplierAddress;
    private  int ContactNumber;

}
