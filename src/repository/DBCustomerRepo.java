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

    public void addCustomerToDB(Customer customer, Statement statement) throws SQLException {
        statement.execute("INSERT INTO customer_table " + "(customer_driver_license_number, customer_driver_since_number," +
                "customer_first_name, customer_last_name, customer_zip_code, customer_city, customer_phone_number, " +
                "customer_mobile_number, customer_email)" + ""
                + "VALUES('"
                + customer.getDriverLicenseNumber() + "','"
                + customer.getDriverSinceNumber() + "','"
                + customer.getName() + "','"
                + customer.getLastName() + "','"
                + customer.getZip() + "','"
                + customer.getCity() + "','"
                + customer.getPhone() + "','"
                + customer.getMobilePhone() + "','"
                + customer.getEmail() + "')");
    }

    public void deleteCustomer(Statement statement, ArrayList<Customer> customerList, String answer) throws SQLException {
        statement.execute("DELETE FROM customer_table WHERE customer_driver_license_number = '" + answer + "'");
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getDriverLicenseNumber().equalsIgnoreCase(answer)) {
                customerList.remove(i);
            }
        }
    }

    public void updateCustomer(Statement statement, String dbColumn, String newValue, String answer) {
        try {
            statement.execute("UPDATE customer_table SET " +
                    dbColumn + " = '" + newValue + "' " +
                    "WHERE customer_driver_license_number ='" + answer + "'");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update customer table");
        }

    }

}
