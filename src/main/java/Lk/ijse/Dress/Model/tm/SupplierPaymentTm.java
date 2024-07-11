package Lk.ijse.Dress.Model.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierPaymentTm {
    private  String id1;
    private  String id2;
    private  String name;
    private  double amount;
    private LocalDate date;
    private JFXButton action;
}
