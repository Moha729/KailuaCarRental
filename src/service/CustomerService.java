package service;

import UI.Buttons;
import models.Customer;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerService {
    CustomerService customerService;

    public CustomerService () {
       customerService = new CustomerService();
    }
    public void createCustomer(ArrayList<Customer> customerList){
        customerService.createCustomer(customerList);
    }
    public void populateCustomerToArrayList(Statement statement, ArrayList<Customer> customerList){
        customerService.populateCustomerToArrayList(statement, customerList);
    }
    public void viewCustomer(Statement statement, ArrayList<Customer> customerList, Buttons tools){
        customerService.viewCustomer(statement,customerList,tools);
    }
    public void updateCustomer(Statement statement, ArrayList<Customer> customerList, Scanner userInput){
        customerService.updateCustomer(statement, customerList, userInput);
    }
    public void deleteCustomer(Statement statement, ArrayList<Customer> customerList, Scanner userInput){
        customerService.deleteCustomer(statement, customerList, userInput);
    }
}
