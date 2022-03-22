package repository;

import db.DBManager;
import models.Customer;

import java.sql.*;
import java.util.ArrayList;


public class DBCustomerRepo {

    Connection connection = DBManager.getConnection();

    public void populateCustomerToArrayList(ArrayList<Customer> customerList) {
        try {
            String sql = ("SELECT customer_driver_license_number, customer_driver_since_number,customer_first_name, customer_last_name, " +
                        "customer_zip_code, customer_city, customer_phone_number, customer_mobile_number, customer_email FROM customer_table");
                  PreparedStatement preparedStatement = connection.prepareStatement(sql);
                  ResultSet resultSet = preparedStatement.executeQuery(sql);
            if (resultSet != null)
                while (resultSet.next()) {
                    Customer customer = new Customer(
                            resultSet.getString("customer_driver_license_number"),
                            resultSet.getString("customer_driver_since_number"),
                            resultSet.getString("customer_first_name"),
                            resultSet.getString("customer_last_name"),
                            resultSet.getInt("customer_zip_code"),
                            resultSet.getString("customer_city"),
                            resultSet.getInt("customer_phone_number"),
                            resultSet.getInt("customer_mobile_number"),
                            resultSet.getString("customer_email"));

                    customerList.add(customer);
                }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }

    public void addCustomerToDB(Customer customer)  {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement
                    ("INSERT INTO customer_table " + "" +
                            "(customer_driver_license_number, customer_driver_since_number,customer_first_name, customer_last_name, customer_zip_code, " +
                            "customer_city, customer_phone_number,customer_mobile_number, customer_email)" + "VALUES(?,?,?,?,?,?,?,?,?)");

            preparedStatement.setString(1, customer.getDriverLicenseNumber());
            preparedStatement.setString(2, customer.getDriverSinceNumber());
            preparedStatement.setString(3, customer.getName());
            preparedStatement.setString(4, customer.getLastName());
            preparedStatement.setInt(5, customer.getZip());
            preparedStatement.setString(6, customer.getCity());
            preparedStatement.setInt(7, customer.getPhone());
            preparedStatement.setInt(8, customer.getMobilePhone());
            preparedStatement.setString(9, customer.getEmail());
            preparedStatement.executeUpdate();

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteCustomer(ArrayList<Customer> customerList, String answer) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customer_table WHERE customer_driver_license_number = ?");
        preparedStatement.setString(1, answer);
        preparedStatement.executeUpdate();
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getDriverLicenseNumber().equalsIgnoreCase(answer)) {
                customerList.remove(i);

            }
        }

    }
    public void updateCustomer(String dbColumn, String newValue, String answer)  {
      try {
          PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customer_table SET " + dbColumn + " =? " + " WHERE customer_driver_license_number =?");
          preparedStatement.setString(1, newValue);
          preparedStatement.setString(2, answer);
          preparedStatement.executeUpdate();
          preparedStatement.close();
      }catch (SQLException e){
          System.out.println(e.getMessage());
      }

    }

}
