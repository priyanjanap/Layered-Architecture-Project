package Lk.ijse.Dress.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierPayment {
    private  String id1;
    private  String id2;
    private  String name;
    private  double amount;
    private LocalDate date;
}
