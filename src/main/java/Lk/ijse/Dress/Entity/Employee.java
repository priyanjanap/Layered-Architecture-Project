package Lk.ijse.Dress.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    private String EmployeeId;
    private String EmployeeName;
    private String EmployeeAddress;
    private int EmployeeAge;
    private double Salary;
    private String Gender;
    private Date dateOfBirth;
    private  int ContactNumber;


}
