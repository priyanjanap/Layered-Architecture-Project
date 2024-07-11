package Lk.ijse.Dress.dao.Custom.impl;

import Lk.ijse.Dress.Entity.RentalTable;
import Lk.ijse.Dress.dao.Custom.JoinQuearyDAO;
import Lk.ijse.Dress.dao.SQLUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinQuearyDAOImpl implements JoinQuearyDAO {
    @Override
    public ObservableList<RentalTable> loadRentalAndOrdersData() throws SQLException, ClassNotFoundException {
        ObservableList<RentalTable> rentalList = FXCollections.observableArrayList();

        String joinQuery = "SELECT r.rental_id, r.start_date, r.last_date, c.Customer_Id, c.Customer_name, c.Customer_contact_number, rd.Dressid " +
                "FROM customer c " +
                "JOIN rental r ON c.Customer_Id = r.customer_id " +
                "JOIN rentaldress rd ON r.rental_id = rd.rentalID";


        ResultSet resultSet = SQLUtil.execute(joinQuery);

        while (resultSet.next()) {
            String rentalId = resultSet.getString("rental_id");
            Date startDate = resultSet.getDate("start_date");
            Date lastDate = resultSet.getDate("last_date");
            String customerId = resultSet.getString("Customer_Id");
            String customerName = resultSet.getString("Customer_name");
            int customerContactNumber = resultSet.getInt("Customer_contact_number");
            String dressId = resultSet.getString("Dressid");

            RentalTable rentalTable = new RentalTable(rentalId, customerId, customerName, customerContactNumber, dressId, startDate, lastDate);
            rentalList.add(rentalTable);
        }
        return rentalList;
    }
}



