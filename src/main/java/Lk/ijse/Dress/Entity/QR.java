package Lk.ijse.Dress.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QR {
    private int id;
    private  Byte qrData;
    private String nic;
    private  String empId;
}
