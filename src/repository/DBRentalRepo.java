package repository;

import db.DBManager;
import models.Car;
import models.Customer;
import models.Rental;

import java.sql.*;
import java.util.ArrayList;

public class DBRentalRepo {

    CarRepository carRepository = new CarRepository();
    CustomerRepository customerRepository = new CustomerRepository();
    Connection connection = DBManager.getConnection();

    public void populateRentals(ArrayList<Rental> rentalList, ArrayList<Car> carList, ArrayList<Customer> customerList) {

        try {
            String sql = ("SELECT * FROM rental_table");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
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

            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error in populating rentals: " + e.getMessage() + "\n");
        }
    }

    public void addRentalToDB(Rental rental) {

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("INSERT INTO rental_table " + "(registration_number, customer_driver_license_number,rental_from_date, rental_to_date, rental_max_km)" + "VALUES(?,?,?,?,?)");
            preparedStatement.setString(1, rental.getCar().getRegistrationNumber());
            preparedStatement.setString(2, rental.getCustomer().getDriverLicenseNumber());
            preparedStatement.setString(3, rental.getFromDateAndTime());
            preparedStatement.setString(4, rental.getToDateAndTime());
            preparedStatement.setInt(5, rental.getMaxKm());
            preparedStatement.executeUpdate();

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }




    }
    public void updateRental(String dbColumn, String newValue, int answer) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE rental_table SET " + dbColumn + " =? " + " WHERE rental_id =?");
                preparedStatement.setString(1, newValue);
                preparedStatement.setInt(2, answer);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

    public void deleteRental(int answer){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM rental_table WHERE rental_id = ?");
            preparedStatement.setInt(1, answer);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
