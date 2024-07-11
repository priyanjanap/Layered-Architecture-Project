package Lk.ijse.Dress.dao.Custom;

import Lk.ijse.Dress.dao.SuperDAO;

import java.sql.Blob;
import java.sql.SQLException;

public interface ImageDAO extends SuperDAO {
    boolean saveQRCodeToDatabase(String empId, String nic, byte[] qrCodeByteArray) throws SQLException, ClassNotFoundException;

    public boolean saveEmployeeImage(String empId, byte[] imageData) throws SQLException, ClassNotFoundException;

    public boolean saveImageToDatabase(byte[] imageData) throws ClassNotFoundException, SQLException;

    public Blob getQrCodeData(String nic) throws SQLException, ClassNotFoundException;

    public String getEmpIdByNIC(String nic) throws SQLException, ClassNotFoundException;
    public Blob getEmployeeImage(String empId) throws SQLException,ClassNotFoundException;


}