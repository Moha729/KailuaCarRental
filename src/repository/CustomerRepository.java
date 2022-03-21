package repository;

import UI.UITools;
import models.Customer;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerRepository {

    UITools tools = new UITools();
    DBCustomerRepo dbCustomerRepo = new DBCustomerRepo();

    public Customer getCustomer(ArrayList<Customer> customerList, UITools tools){//MÅ IKKE SLETTES
        viewCustomer(customerList, tools);
        Customer customer = null;
        String customerDV = tools.returnStringInfo(40, 1, "Enter customer driving licence number");

        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getDriverLicenseNumber().equalsIgnoreCase(customerDV)){
                customer = customerList.get(i);
            }
        }
        return customer;
    }
    public Customer getCustomer(ArrayList<Customer> customerList, String customerDV){//MÅ IKKE SLETTES
        Customer customer = null;
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getDriverLicenseNumber().equalsIgnoreCase(customerDV)){
                customer = customerList.get(i);
            }
        }
        return customer;
    }


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
        dbCustomerRepo.addCustomerToDB(customer, statement);

    }

    public void populateCustomerToArrayList(Statement statement, ArrayList<Customer> customerList){
        dbCustomerRepo.populateCustomerToArrayList(statement, customerList);
    }
    public void addCustomerToDB(Customer customer, Statement statement)throws  SQLException{
        dbCustomerRepo.addCustomerToDB(customer, statement);
    }

    public void viewCustomer(ArrayList<Customer> customerList, UITools tools){
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

        Customer customer = new Customer();

        for (int i = 0; i < customerList.size(); i++) {
            System.out.println(customerList.get(i));
        }

        System.out.println("Enter which driver license number to be updated");
        String answer = userInput.next();

        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getDriverLicenseNumber().equalsIgnoreCase(answer)) {
                System.out.println(customerList.get(i));
                customer = customerList.get(i);

            }
        }

        //Customer customer1= getCustomer(customerList, tools);


        System.out.println("What do you want to update?\n" +
                "1 for DrLicNumb\n2 for DrSincDate\n3 for fName\n4 for lName\n5 for zipCode" +
                "\n6 for city\n7 for pNumb\n8 for mNumb\n9 for email");

        int ans = userInput.nextInt();

        String newValue = null;
        String dbColumn = null;

        switch (ans){

            case 1 :
                System.out.println("Enter which driver license number to be updated");
                newValue = userInput.next();
                dbColumn = "customer_driver_license_number";
                customer.setDriverLicenseNumber(newValue);
                break;
            case 2 :
                System.out.println("Enter customer driver since number");
                newValue =  userInput.next();
                dbColumn = "customer_driver_since_number";
                customer.setDriverSinceNumber(newValue);
                break;
            case 3 :
                System.out.println("Enter new first name");
                newValue = userInput.next();
                dbColumn = "customer_first_name";
                customer.setName(newValue);
                break;
            case 4 :
                System.out.println("Enter new last name");
                newValue = userInput.next();
                dbColumn = "customer_last_name";
                customer.setLastName(newValue);
                break;
            case 5 :
                System.out.println("Enter new customer zip code");
                int newZip = userInput.nextInt();
                newValue = String.valueOf(newZip);
                dbColumn = "customer_zip_code";
                customer.setZip(newZip);
                break;
            case 6 :
                System.out.println("Enter new city");
                newValue = userInput.next();
                dbColumn = "customer_city";
                customer.setCity(newValue);
                break;
            case 7 :
                System.out.println("Enter new customer phone number");
                int newPhone = userInput.nextInt();
                newValue = String.valueOf(newPhone);
                dbColumn = "customer_phone_number";
                customer.setPhone(newPhone);

                break;
            case 8 :
                System.out.println("Enter new customer mobile number");
                int newMobile = userInput.nextInt();
                newValue = String.valueOf(newMobile);
                dbColumn = "customer_mobile_number";
                customer.setMobilePhone(newMobile);

                break;
            case 9 :
                System.out.println("Enter new customer email");
                newValue = userInput.next();
                dbColumn = "customer_email";
                customer.setEmail(newValue);
                break;
        }
        dbCustomerRepo.updateCustomer(statement, dbColumn, newValue, answer);
    }

    public void deleteCustomer(Statement statement, ArrayList<Customer> customerList, UITools tools) throws SQLException{
        String answer;
        viewCustomer(customerList, tools);
        String DriverNumb = tools.returnStringInfo(50, 1, "Enter the driver license number for the customer you want to delete");
        answer = DriverNumb;

        dbCustomerRepo.deleteCustomer(statement, customerList, answer);

        System.out.println("Customer deleted!");
        }
    }

