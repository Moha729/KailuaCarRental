package service;

import UI.Buttons;
import models.Customer;
import repository.CustomerRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerService {
    CustomerRepository customerRepository;

    public CustomerService(){
        customerRepository = new CustomerRepository();
    }
    public void createCustomer(ArrayList<Customer> customerList){
        customerRepository.createCustomer(customerList);
    }
    public void populateCustomerToArrayList(Statement statement, ArrayList<Customer> customerList){
        customerRepository.populateCustomerToArrayList(statement, customerList);
    }
    public void viewCustomer(Statement statement, ArrayList<Customer> customerList, Buttons tools){
        customerRepository.viewCustomer(statement,customerList,tools);
    }
    public void updateCustomer(Statement statement, ArrayList<Customer> customerList, Scanner userInput){
        customerRepository.updateCustomer(statement, customerList, userInput);
    }
    public void deleteCustomer(Statement statement, ArrayList<Customer> customerList, Scanner userInput) throws SQLException {
        customerRepository.deleteCustomer(statement, customerList, userInput);
    }
}
