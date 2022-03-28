package repository;

import UI.UITools;
import models.Customer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerRepository {

    UITools tools = new UITools();
    DBCustomerRepo dbCustomerRepo = new DBCustomerRepo();

    public Customer getCustomer(ArrayList<Customer> customerList, UITools tools){
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
    public Customer getCustomer(ArrayList<Customer> customerList, String customerDV){
        Customer customer = null;
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getDriverLicenseNumber().equalsIgnoreCase(customerDV)){
                customer = customerList.get(i);
            }
        }
        return customer;
    }


    public void createCustomer(ArrayList<Customer> customerList) throws SQLException {

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
        addCustomerToDB(customer);
    }

    public void populateCustomerToArrayList(ArrayList<Customer> customerList){
        dbCustomerRepo.populateCustomerToArrayList(customerList);
    }
    public void addCustomerToDB(Customer customer){
        dbCustomerRepo.addCustomerToDB(customer);
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

    public void updateCustomer(ArrayList<Customer> customerList, Scanner userInput) {

        Customer customer = getCustomer(customerList, tools);

        tools.customizedButton(60,2, "What do you want to update?");
        System.out.println();
        tools.customizedButton(39,3,"" +
                   "\t\t1 to First name\t\t\t\t\t|" +
                "\n|\t\t2 for Last name\t\t\t\t\t|" +
                "\n|\t\t3 for zip-code\t\t\t\t\t|\n|\t\t4 for city\t\t\t\t\t\t|"+
                "\n|\t\t5 for phone number\t\t\t\t|\n|\t\t6 for mobile number\t\t\t\t|"+
                "\n|\t\t7 for E-mail\t\t\t\t\t");

        int ans = userInput.nextInt();

        String newValue = null;
        String dbColumn = null;

        switch (ans){

            case 1 :
                System.out.println("Enter new first name");
                newValue = userInput.next();
                dbColumn = "customer_first_name";
                customer.setName(newValue);
                break;
            case 2 :
                System.out.println("Enter new last name");
                newValue = userInput.next();
                dbColumn = "customer_last_name";
                customer.setLastName(newValue);
                break;
            case 3 :
                System.out.println("Enter new customer zip code");
                int newZip = userInput.nextInt();
                newValue = String.valueOf(newZip);
                dbColumn = "customer_zip_code";
                customer.setZip(newZip);
                break;
            case 4 :
                System.out.println("Enter new city");
                newValue = userInput.next();
                dbColumn = "customer_city";
                customer.setCity(newValue);
                break;
            case 5 :
                System.out.println("Enter new customer phone number");
                int newPhone = userInput.nextInt();
                newValue = String.valueOf(newPhone);
                dbColumn = "customer_phone_number";
                customer.setPhone(newPhone);
                break;
            case 6 :
                System.out.println("Enter new customer mobile number");
                int newMobile = userInput.nextInt();
                newValue = String.valueOf(newMobile);
                dbColumn = "customer_mobile_number";
                customer.setMobilePhone(newMobile);
                break;
            case 7 :
                System.out.println("Enter new customer email");
                newValue = userInput.next();
                dbColumn = "customer_email";
                customer.setEmail(newValue);
                break;
        }
        dbCustomerRepo.updateCustomer(dbColumn, newValue, customer.getDriverLicenseNumber());
    }

    public void deleteCustomer(ArrayList<Customer> customerList, UITools tools) throws SQLException{
        String answer;
        viewCustomer(customerList, tools);
        String DriverNumb = tools.returnStringInfo(50, 1, "Enter the driver license number for the customer you want to delete");
        answer = DriverNumb;

        dbCustomerRepo.deleteCustomer(customerList, answer);

        System.out.println("Customer deleted!");
        }
    }

