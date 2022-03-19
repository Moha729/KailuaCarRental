package controller;

import UI.UITools;
import db.DBManager;
import models.Car;
import models.Customer;
import models.Rental;
import service.CarService;
import service.CustomerService;
import service.RentalService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleController {

    UITools tools = new UITools();
    Scanner userInput = new Scanner(System.in);
    Connection connection = DBManager.getConnection();
    CarService carService = new CarService();
    CustomerService customerService = new CustomerService();
    RentalService rentalService = new RentalService();
    ArrayList<Car> carList = new ArrayList<>();
    ArrayList<Customer> customerList = new ArrayList<>();
    ArrayList<Rental> rentalList = new ArrayList<>();

    public void run() {

        Statement statement;

        try {
            statement = connection.createStatement();

            carService.populateCars(statement, carList);
            customerService.populateCustomerToArrayList(statement, customerList);
            rentalService.populateRentalContractsToArrayList(statement, rentalList, carList, customerList);

            runMenu(statement);
        } catch (SQLException e) {
            System.out.println("No connection" + e.getMessage());
        }
    }


    public void runMenu(Statement statement) {

        tools.customizedButton(120, 7, "Welcome to Kailua car rental");

        System.out.print(tools.doubleButton(">1< Cars", ">2< Customers"));
        System.out.print(tools.doubleButton(">3< Rentals", ">4< Exit"));
        int answer = userInput.nextInt();

        switch (answer) {
            case 1 -> runCarMenu(statement);
            case 2 -> customerMenu(statement);
            case 3 -> rentalMenu(statement);
            case 4 -> closeProgram(statement);
            case 0 -> closeProgram(statement);
        }
        continueButton(statement);
    }

    public void closeProgram(Statement statement) {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tools.customizedButton(120, 1, "System closed");
        System.exit(0);
    }

    public void continueButton(Statement statement) {
        tools.customizedButton(15, 1, ">1< continue..");
        System.out.print(" ");
        int start = userInput.nextInt();
        if (start != 0) {
            runMenu(statement);
        }
    }

    public void runCarMenu(Statement statement) {
        try {
            System.out.println();
            tools.customizedButton(120, 3, "Cars");

            System.out.print(tools.doubleButton(">1< See cars", ">2< Update car"));
            System.out.print(tools.doubleButton(">3< New car", ">4< \"Delete car\""));

            int answer = userInput.nextInt();

            switch (answer) {
                case 1 -> carService.viewCars(carList, tools);
                case 2 -> carService.updateCar(statement, userInput, carList, tools);
                case 3 -> carService.createCar(statement, userInput, carList, tools);
                case 4 -> carService.delete(statement, carList, tools);
                case 0 -> runMenu(statement);

            }
        } catch (SQLException sqlEx) {
            System.out.println("Error in Cars_main_menu: " + sqlEx);
        }
        continueButton(statement);
    }

    public void customerMenu(Statement statement) {
        Customer customer = new Customer();

        try {
            System.out.println();
            tools.customizedButton(120, 3, "Customers");
            System.out.print(tools.doubleButton(">1< See customers", ">2< Update a customer"));
            System.out.print(tools.doubleButton(">3< Create a new customer", ">4< \"Delete a customer\""));

            int answer = userInput.nextInt();
            switch (answer) {
                case 1 -> customerService.viewCustomer(customerList, tools);
                case 2 -> customerService.updateCustomer(statement, customerList, userInput, customer);
                case 3 -> customerService.createCustomer(statement, customerList);
                case 4 -> customerService.deleteCustomer(statement, customerList, tools);
                case 0 -> runMenu(statement);
            }
        } catch (SQLException sqlEx) {
            System.out.println("Error in Customer maim menu: " + sqlEx);
        }
        continueButton(statement);
    }


    public void rentalMenu(Statement statement) {

        System.out.println();
        tools.customizedButton(120, 3, "Rentals");
        System.out.print(tools.doubleButton(">1< New rental", ">2< Active rentals"));
        System.out.print(tools.doubleButton(">3< Change rental", ">4< End rental"));

        int answer = userInput.nextInt();

        try {
            switch (answer) {
                case 1 -> rentalService.createRentalContract(rentalList, carList, customerList, statement);
                case 2 -> rentalService.viewRentals(rentalList, tools);
                case 3 -> rentalService.updateRentalContracts(statement, rentalList, userInput, carList);
                case 4 -> rentalService.deleteRentalContract(statement, rentalList, userInput);
                case 0 -> runMenu(statement);
                default -> rentalMenu(statement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        continueButton(statement);
    }
}




