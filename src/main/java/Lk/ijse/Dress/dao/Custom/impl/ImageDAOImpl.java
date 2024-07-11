package Lk.ijse.Dress.dao.Custom.impl;

import Lk.ijse.Dress.dao.Custom.ImageDAO;
import Lk.ijse.Dress.dao.SQLUtil;

import java.sql.*;

public class ImageDAOImpl implements ImageDAO {
    @Override
    public  boolean saveImageToDatabase(byte[] imageData) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO images (image_data) VALUES (?)";

        int re=SQLUtil.execute(sql,imageData)
;
    return  re>0;}

    @Override
    public Blob getQrCodeData(String nic) throws SQLException, ClassNotFoundException {
        String sql = "SELECT qr_code_data FROM qr_codes WHERE nic_number = ?";



            ResultSet resultSet = SQLUtil.execute(sql,nic);

            if (resultSet.next()) {
                return resultSet.getBlob("qr_code_data");
            }

        return null;
    }

    @Override
    public String getEmpIdByNIC(String nic) throws SQLException, ClassNotFoundException {
        String sql = "SELECT Employee_ID FROM employee WHERE nic_number = ?";


            ResultSet resultSet =SQLUtil.execute(sql,nic);

            if (resultSet.next()) {
                return resultSet.getString("Employee_ID");
            }

        return null;
    }

    @Override
    public Blob getEmployeeImage(String empId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT image_data FROM employeeimages WHERE empId = ?";


            ResultSet resultSet = SQLUtil.execute(sql,empId);

            if (resultSet.next()) {
                return resultSet.getBlob("image_data");
            }

        return null;    }


    @Override
    public boolean saveQRCodeToDatabase(String empId, String nic, byte[] qrCodeByteArray)throws  SQLException,ClassNotFoundException {
        String sql = "INSERT INTO qr_codes (qr_code_data, nic_number, empId) VALUES (?, ?, ?)";

        int rowsAffected = SQLUtil.execute(sql, qrCodeByteArray, nic, empId);

        return rowsAffected > 0;
    }
    @Override
    public boolean saveEmployeeImage(String empId, byte[] imageData) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO employeeimages (empId, image_data) VALUES (?, ?)";

        int rowsAffected = SQLUtil.execute(sql, empId, imageData);

        return rowsAffected > 0;
    }
}
