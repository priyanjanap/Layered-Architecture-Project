package Lk.ijse.Dress.dao.Custom.impl;

import Lk.ijse.Dress.Entity.Rental;
import Lk.ijse.Dress.Entity.RentalDress;
import Lk.ijse.Dress.dao.Custom.RentalDAO;
import Lk.ijse.Dress.dao.SQLUtil;
import javafx.scene.control.Alert;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.List;

public class RentalDAOImpl implements RentalDAO {

    @Override
    public List<Rental> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean add(Rental rental) throws SQLException {
        String insertQuery = "INSERT INTO rental VALUES (?, ?, ?, ?, ?, ?)";

        try {
            return SQLUtil.execute(insertQuery,
                    rental.getRentalId(),
                    rental.getCustomeid(),
                    rental.getPaymentId(),
                    Date.valueOf(rental.getStDate()),
                    Date.valueOf(rental.getLastDate()),
                    rental.getTimeDuration());
        } catch (SQLException | ClassNotFoundException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                System.err.println("Error: Duplicate rental ID");
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } else {
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public boolean update(Rental rental) throws SQLException {
        String updateQuery = "UPDATE rental SET customeid = ?, payment_id = ?, start_date = ?, last_date = ?, time_duration = ? WHERE rental_id = ?";

        try {
            return SQLUtil.execute(updateQuery,
                    rental.getCustomeid(),
                    rental.getPaymentId(),
                    Date.valueOf(rental.getStDate()),
                    Date.valueOf(rental.getLastDate()),
                    rental.getTimeDuration(),
                    rental.getRentalId());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String rentalId) throws SQLException {
        String deleteQuery = "DELETE FROM rental WHERE rental_id = ?";

        try {
            return SQLUtil.execute(deleteQuery, rentalId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    public  String getAllRentalIds() throws SQLException {
        String sql = "SELECT rental_id FROM rental ORDER BY rental_id DESC LIMIT 1";

        try (ResultSet resultSet = SQLUtil.execute(sql)) {
            if (resultSet.next()) {
                return resultSet.getString("rental_id");
            } else {
                return null;
            }
        }
    }

    @Override
    public int getRentalCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) AS rental_count FROM rental";
        try (
             ResultSet resultSet = SQLUtil.execute(sql)) {
            if (resultSet.next()) {
                return resultSet.getInt("rental_count");
            }
        }
        return 0;
    }
    @Override
    public  boolean save(List<Lk.ijse.Dress.Entity.RentalDress> odList) throws SQLException, ClassNotFoundException {
        for (Lk.ijse.Dress.Entity.RentalDress od : odList) {
            boolean isSaved = save( od);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }
    public boolean save(RentalDress od) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO rentaldress (rentalID, DressId, price_per_day, stdate, lastDate) " +
                "VALUES (?, ?, ?, ?, ?)";
        int rowsAffected = SQLUtil.execute(sql, od.getRentalID(), od.getDressId(), od.getPrice_per_day(), od.getStdate(), od.getLastDate());

        return rowsAffected > 0;
    }

}

