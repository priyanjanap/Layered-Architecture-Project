package Lk.ijse.Dress.DTO;

import Lk.ijse.Dress.Model.Enum.PunStatus;
import Lk.ijse.Dress.Model.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeAttDTO {
    private  String id1;
    private  String id2;
    private LocalDate date;
    private Status status;
    private PunStatus status2;



}
