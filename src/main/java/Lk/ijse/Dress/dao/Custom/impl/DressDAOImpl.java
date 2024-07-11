package Lk.ijse.Dress.dao.Custom.impl;

import Lk.ijse.Dress.Entity.Dress;
import Lk.ijse.Dress.Entity.RentalDress;
import Lk.ijse.Dress.dao.Custom.DressDAO;
import Lk.ijse.Dress.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DressDAOImpl implements DressDAO {
    @Override
    public List<Dress> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM dress";

        try (ResultSet rst = SQLUtil.execute(sql)) {
            List<Dress> rentalList = new ArrayList<>();
            while (rst.next()) {
                Dress rental = new Dress(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getInt(3),
                        rst.getDouble(4),
                        rst.getDate(5),
                        rst.getDate(6)
                );
                rentalList.add(rental);
            }
            return rentalList;
        }
    }
    @Override
    public boolean add(Dress entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Dress entity) throws SQLException, ClassNotFoundException {
     return  false;

}
    public static boolean update(List<Lk.ijse.Dress.Entity.RentalDress> odList) throws SQLException, ClassNotFoundException {
        for (Lk.ijse.Dress.Entity.RentalDress od : odList) {
            boolean isUpdateQty = updateDress(od );
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }
    public static boolean updateDress(RentalDress rd) throws SQLException, ClassNotFoundException {


        String sql = "UPDATE dress SET  Rental_date = ?, ReturnDate = ? WHERE Dress_Id = ?";

        return SQLUtil.execute(sql,rd.getDressId(),rd.getStdate(),rd.getLastDate());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<String> getALLDressIDS() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Dress_Id FROM dress";

        try (
             ResultSet resultSet = SQLUtil.execute(sql)) {

            List<String> idList = new ArrayList<>();
            while (resultSet.next()) {
                idList.add(resultSet.getString("Dress_Id"));
            }
            return idList;
        }    }
}
