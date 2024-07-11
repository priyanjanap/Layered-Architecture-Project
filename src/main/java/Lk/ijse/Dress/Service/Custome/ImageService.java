package Lk.ijse.Dress.Service.Custome;

import Lk.ijse.Dress.Service.AllService;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.SQLException;

public interface ImageService extends AllService {
    public boolean saveQRCodeToDatabase(String empId, String nic, byte[] qrCodeByteArray)throws SQLException,ClassNotFoundException;
    public boolean saveEmployeeImage(String empId, byte[] imageData) throws SQLException, ClassNotFoundException ;
    public  boolean saveImageToDatabase(byte[] imageData) throws ClassNotFoundException, SQLException;
    public void loadQrImage(String nic, ImageView imageEmployeeOR) throws SQLException, IOException, ClassNotFoundException;
    public void loadEmployeeImage(String nic, ImageView imageEmployeePhoto) throws SQLException, IOException, ClassNotFoundException;


}
