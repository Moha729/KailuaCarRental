package service;

import UI.MoTools;
import controller.ConsoleController;
import models.Car;
import models.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerService {
    MoTools tools = new MoTools();
    Scanner userInput = new Scanner(System.in);

    public void createCustomer(ArrayList<Customer> customerList){

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


    }
    public void populateCustomerToArrayList(Statement statement, ArrayList<Customer> customerList){
        try {

            String sql = ("SELECT * FROM car_table INNER JOIN luxury_cars ON car_table.registration_number = luxury_cars.registration_number");
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

    public void viewCustomer(Statement statement, ArrayList<Customer> customerList, MoTools tools){
        System.out.println();
        tools.customizedButton(50,1, "Customer");

        tools.margeTop(70);
        System.out.printf("\n| %-14s %-14s %-12s %-12s %-12s %-10s %-10s %-13s %-13s |\n",
                "DriverNumb", "DriverSince", "Fname", "Lname", "ZipCode", "CustomCity", "PhoneNumb", "MobileNumb",
                "Email");
        tools.margeTop(120);

        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getClass().getSimpleName().equals("Luxury")) {
                System.out.println("\n" + customerList.get(i).toString());
                tools.margeTop(120);
            }
        }
        System.out.println();
    }

    public void deleteCustomer(Statement statement, ArrayList<Customer> customerList, Scanner userInput){
        System.out.println(customerList);
        String answer = userInput.next();
        System.out.println("Enter the driver license number for the customer you want to delete");

        //if (answer.equalsIgnoreCase())
    }
}
