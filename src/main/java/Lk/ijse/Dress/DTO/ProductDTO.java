package Lk.ijse.Dress.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {

    private  String id;
    private  String name;
    private  int qty;
    private  double price;
    private  String description;
    private  String type;
    private  String category;



}
