package repository;

import db.DBManager;
import models.Car;
import models.Customer;
import models.Rental;
import models.Sport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBRentalRepo {

    CarRepository carRepository = new CarRepository();
    CustomerRepository customerRepository = new CustomerRepository();

    public void addRentalToDB(Rental rental, Statement statement){

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

    public void populateRentals (ArrayList<Rental> rentalList, Statement statement,
                                 ArrayList<Car> carList, ArrayList<Customer> customerList){

        try {

            String sql = ("SELECT * FROM rental_table");
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null)
                while (resultSet.next()) {
                    int rentalId = resultSet.getInt("rental_id");
                    String regNum = resultSet.getString("registration_number");
                    Car rentalCar = carRepository.getCar(carList, regNum);

                    String drivLNum = resultSet.getString("customer_driver_license_number");
                    Customer rentalCustomer = customerRepository.getCustomer(customerList, drivLNum);

                    String fromDate = resultSet.getString("rental_from_date");
                    String toDate = resultSet.getString("rental_to_date");
                    int maxKM = resultSet.getInt("rental_max_km");

                    Rental rental = new Rental(rentalCar, rentalCustomer, rentalId, fromDate, toDate, maxKM);

                    rentalList.add(rental);
                }

//            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error in populating rentals: "+e.getMessage() + "\n");
        }

    }
}
