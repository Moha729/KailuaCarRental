package repository;

import UI.Buttons;
import models.Car;
import models.Customer;
import service.CustomerService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerRepository {

    Buttons tools = new Buttons();

    public void createCustomer(Statement statement, ArrayList<Customer> customerList) throws SQLException {

        String name = tools.returnStringInfo(50, 1, "Enter first name");

        String lastName = tools.returnStringInfo(50, 1, "Enter last name");

        int zip = tools.returnIntInfo(50, 1, "Enter zip code ");

        String city = tools.returnStringInfo(50, 1, "Enter city name");

        int phone = tools.returnIntInfo(50, 1, "Enter phone number");

        int mobilePhone = tools.returnIntInfo(50, 1, "Enter mobile phone number");

        String email = tools.returnStringInfo(50, 1, "Enter email");

        String driverLicenseNumber = tools.returnStringInfo(50, 1, "Enter driver license number");

        String driverSinceNumber = tools.returnStringInfo(50, 1, "Enter driver since number");

        Customer customer = new Customer(driverLicenseNumber, driverSinceNumber, name, lastName, zip,
                city, phone, mobilePhone, email);
        customerList.add(customer);
        addCustomerToDB(customer, statement);

    }
    public void addCustomerToDB(Customer customer, Statement statement)throws  SQLException{
        statement.execute("INSERT INTO customer_table " + "(customer_driver_license_number, customer_driver_since_number," +
                "customer_first_name, customer_last_name, customer_zip_code, customer_city, customer_phone_number, " +
                "customer_mobile_number, customer_email)" + ""
                +"VALUES('"
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

    public void populateCustomerToArrayList(Statement statement, ArrayList<Customer> customerList){
        try {

            String sql = ("SELECT * FROM customer_table");
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null)
                while (resultSet.next()) {
                    Customer customer = new Customer(
                            resultSet.getString("customer_driver_license_number"),
                            resultSet.getString("customer_driver_since_number"),
                            resultSet.getString("customer_first_name "),
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

    public void viewCustomer(Statement statement, ArrayList<Customer> customerList, Buttons tools){
        System.out.println();
        tools.customizedButton(50,1, "Customer");

        tools.margeTop(70);
        System.out.printf("\n| %-14s %-14s %-12s %-12s %-12s %-10s %-10s %-13s %-13s |\n",
                "DriverNumb", "DriverSince", "Fname", "Lname", "ZipCode", "CustomCity", "PhoneNumb", "MobileNumb",
                "Email");
        tools.margeTop(120);

        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getClass().getSimpleName().equals("Customer")) {
                System.out.println("\n" + customerList.get(i));
                tools.margeTop(120);
            }
        }
        System.out.println();
    }

    public void updateCustomer(Statement statement, ArrayList<Customer> customerList, Scanner userInput){

        for (int i = 0; i < customerList.size(); i++) {
            System.out.println(customerList.get(i));
        }

        System.out.println("Enter which driver license number to be updated");
        String answer = userInput.next();

        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getDriverLicenseNumber().equalsIgnoreCase(answer))
                System.out.println(customerList.get(i));
        }
        System.out.println("What do you want to update?\n" +
                "1 for DrLicNumb\n2 for DrSincDate\n3 for fName\n4 for lName\n5 for zipCode" +
                "\n6 for city\n7 for pNumb\n 8 for mNumb\n9 for email");

        int ans = userInput.nextInt();

        String newValue = null;
        String newVariable = null;

        switch (ans){

            case 1 :
                System.out.println("Enter which driver license number to be updated");
                newValue = userInput.next();
                newVariable = "customer_driver_license_number";
                break;
            case 2 :
                System.out.println("Enter customer driver since number");
                newValue =  userInput.next();
                newVariable = "customer_driver_since_number";
                break;
            case 3 :
                System.out.println("Enter new first name");
                newValue = userInput.next();
                newVariable = "customer_first_name";
                break;
            case 4 :
                System.out.println("Enter new last name");
                newValue = userInput.next();
                newVariable = "customer_last_name";
                break;
            case 5 :
                System.out.println("Enter new customer zip code");
                newValue = userInput.next();
                newVariable = "customer_zip_code";
                break;
            case 6 :
                System.out.println("Enter new city");
                newValue = userInput.next();
                newVariable = "customer_city";
                break;
            case 7 :
                System.out.println("Enter new customer phone number");
                newValue = userInput.next();
                newVariable = "customer_phone_number";
                break;
            case 8 :
                System.out.println("Enter new ccustomer mobile number");
                newValue = userInput.next();
                newVariable = "customer_mobile_number";
                break;
            case 9 :
                System.out.println("Enter new customer email");
                newValue = userInput.next();
                newVariable = "customer_email";
                break;
        }

        try {
            statement.execute("UPDATE customer_table SET " +
                    newVariable + " = '" + newValue + "' " +
                    "WHERE customer_driver_license_number ='" + answer + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update customer table");
        }
        //statement.close();
    }

    public void deleteCustomer(Statement statement, ArrayList<Customer> customerList, Scanner userInput) throws SQLException{
        System.out.println("Enter the driver license number for the customer you want to delete");
        System.out.println(customerList);
        String answer = userInput.next();

        statement.execute("DELETE FROM customer_table WHERE customer_driver_license_number = '" + answer + "'");
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getDriverLicenseNumber().equalsIgnoreCase(answer)) {
                customerList.remove(i);
            }
        }
    }
}
