package Lk.ijse.Dress.Service.Custome.Impl;

import Lk.ijse.Dress.Service.Custome.ImageService;
import Lk.ijse.Dress.dao.Custom.ImageDAO;
import Lk.ijse.Dress.dao.DAOFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class ImageServiceImpl implements ImageService{
    ImageDAO imageDAO= (ImageDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Image);
    @Override
    public boolean saveQRCodeToDatabase(String empId, String nic, byte[] qrCodeByteArray) throws SQLException, ClassNotFoundException {
        return imageDAO.saveQRCodeToDatabase(empId,nic,qrCodeByteArray);
    }

    @Override
    public boolean saveEmployeeImage(String empId, byte[] imageData) throws SQLException, ClassNotFoundException {
        return imageDAO.saveEmployeeImage(empId,imageData);
    }

    @Override
    public boolean saveImageToDatabase(byte[] imageData) throws ClassNotFoundException, SQLException {
        return imageDAO.saveImageToDatabase(imageData);
    }

    @Override
    public void loadQrImage(String nic, ImageView imageEmployeeOR) throws SQLException, IOException, ClassNotFoundException {
        Blob blob = imageDAO.getQrCodeData(nic);
        if (blob != null) {
            try (InputStream inputStream = blob.getBinaryStream()) {
                BufferedImage bufferedImage = ImageIO.read(inputStream);

                if (bufferedImage != null) {
                    int width = bufferedImage.getWidth();
                    int height = bufferedImage.getHeight();

                    WritableImage fxImage = new WritableImage(width, height);
                    PixelWriter pixelWriter = fxImage.getPixelWriter();

                    for (int y = 0; y < height; y++) {
                        for (int x = 0; x < width; x++) {
                            int argb = bufferedImage.getRGB(x, y);
                            pixelWriter.setArgb(x, y, argb);
                        }
                    }

                    imageEmployeeOR.setImage(fxImage);
                }
            }
        } else {
            System.out.println("No image found for NIC: " + nic);
        }
    }

    @Override
    public void loadEmployeeImage(String nic, ImageView imageEmployeePhoto) throws SQLException, IOException, ClassNotFoundException {
        String empId = imageDAO.getEmpIdByNIC(nic);
        if (empId != null) {
            Blob blob = imageDAO.getEmployeeImage(empId);

            if (blob != null) {
                try (InputStream inputStream = blob.getBinaryStream()) {
                    BufferedImage bufferedImage = ImageIO.read(inputStream);

                    if (bufferedImage != null) {
                        int width = bufferedImage.getWidth();
                        int height = bufferedImage.getHeight();

                        WritableImage fxImage = new WritableImage(width, height);
                        PixelWriter pixelWriter = fxImage.getPixelWriter();

                        for (int y = 0; y < height; y++) {
                            for (int x = 0; x < width; x++) {
                                int argb = bufferedImage.getRGB(x, y);
                                pixelWriter.setArgb(x, y, argb);
                            }
                        }

                        imageEmployeePhoto.setImage(fxImage);
                    }
                }
            } else {
                System.out.println("No image found for employee ID: " + empId);
            }
        } else {
            System.out.println("Employee ID not found for NIC: " + nic);
        }
    }
}

