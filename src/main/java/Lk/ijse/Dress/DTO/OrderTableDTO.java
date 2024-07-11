package Lk.ijse.Dress.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderTableDTO {
    private String orderId;
    private Date date;
    private String cusid;
    private String cusname;
    private int number;

}
