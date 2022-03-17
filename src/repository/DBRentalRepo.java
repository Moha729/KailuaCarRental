package repository;

import db.DBManager;
import models.Customer;
import models.Rental;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBRentalRepo {

    //Connection connection = DBManager.getConnection();//returns connection

    public void addRentalToDB(Rental rental, Statement statement){
        /*Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No connection");
        }*/
        try {
            statement.execute("INSERT INTO rental_table " + "(registration_number, customer_driver_license_number," +
                    "rental_from_date, rental_to_date, rental_max_km)" + ""
                    + "VALUES('"
                    + rental.getCar().getRegistrationNumber() + "','"
                    + rental.getCustomer().getDriverLicenseNumber() + "','"
                    + rental.getFromDateAndTime() + "','"
                    + rental.getToDateAndTime() + "','"
                    + rental.getMaxKm() +  "')");
        } catch (SQLException e) {
            System.out.println("Error not added rental to db:\n" + e);
        }

    }
}
