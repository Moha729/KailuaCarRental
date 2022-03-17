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
    public void populateCustomerToArrayList(Statement statement, ArrayList<Customer> customerList){
        customerRepository.populateCustomerToArrayList(statement, customerList);
    }
    public void viewCustomer(ArrayList<Customer> customerList, UITools tools){
        customerRepository.viewCustomer(customerList,tools);
    }
    public void updateCustomer(Statement statement, ArrayList<Customer> customerList, Scanner userInput,Customer customer){
        customerRepository.updateCustomer(statement, customerList, userInput,customer);
    }
    public void deleteCustomer(Statement statement, ArrayList<Customer> customerList, UITools tools) throws SQLException {
        customerRepository.deleteCustomer(statement, customerList, tools);
    }
}
