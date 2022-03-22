package service;

import UI.UITools;
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
    public void createCustomer(Statement statement, ArrayList<Customer> customerList) throws SQLException {
        customerRepository.createCustomer(statement,customerList);
    }
    public void populateCustomerToArrayList(ArrayList<Customer> customerList){
        customerRepository.populateCustomerToArrayList(customerList);
    }
    public void viewCustomer(ArrayList<Customer> customerList, UITools tools){
        customerRepository.viewCustomer(customerList,tools);
    }
    public void updateCustomer(Statement statement, ArrayList<Customer> customerList, Scanner userInput){
        customerRepository.updateCustomer(statement, customerList, userInput);
    }
    public void deleteCustomer(Statement statement, ArrayList<Customer> customerList, UITools tools) throws SQLException {
        customerRepository.deleteCustomer(statement, customerList, tools);
    }
}
