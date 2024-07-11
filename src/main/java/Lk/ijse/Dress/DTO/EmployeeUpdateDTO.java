package Lk.ijse.Dress.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeUpdateDTO {

    private  String name;

    private  String address;

    private  double salary;
    private  int  tell;
    private  String employeeiD;
}
