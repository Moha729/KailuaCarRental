package controller;

import UI.UITools;
import db.DBManager;
import models.*;
import service.*;
import java.sql.*;
import java.util.*;

public class ConsoleController {

    ArrayList<Car> carList = new ArrayList<>();
    ArrayList<Customer> customerList = new ArrayList<>();
    ArrayList<Rental> rentalList = new ArrayList<>();

    CarService carService = new CarService();
    CustomerService customerService = new CustomerService();
    RentalService rentalService = new RentalService();

    UITools menuTools = new UITools();
    Scanner userInput = new Scanner(System.in);
    Connection connection = DBManager.getConnection();

    public void run() {
        try {
            Statement statement;
            statement = connection.createStatement();

            carService.populateCars(carList);
            customerService.populateCustomerToArrayList(statement, customerList);
            rentalService.populateRentalContractsToArrayList(statement, rentalList, carList, customerList);

            runMenu(statement);
        } catch (SQLException e) {
            System.out.println("No connection" + e.getMessage());
        }
    }

    public void runMenu(Statement statement) {

        switch (menuTools.menuOptions("Welcome to Kailua car rental",
                ">1< Rentals", ">2< Cars", ">3< Customers", ">4< Exit")) {
            case 1 -> rentalMenu(statement);
            case 2 -> CarMenu(statement);
            case 3 -> customerMenu(statement);
            case 4,0 -> menuTools.closeProgram(statement,connection);
        }
        continueButton(statement);
    }

    public void CarMenu(Statement statement) {
        try {
            menuTools.carMenuOptions();
            int intType = userInput.nextInt();
            switch (intType) {
                case 1 -> carService.viewCars(carList, menuTools);
                case 2 -> carService.updateCar(statement, userInput, carList, menuTools);
                case 3 -> carService.createCar(statement, userInput, carList, menuTools);
                case 4 -> carService.delete(statement, carList, menuTools);
                case 0 -> runMenu(statement);
            }
        } catch (SQLException sqlEx) {
            System.out.println("Error in Cars_main_menu: " + sqlEx);
        }
        continueButton(statement);
    }

    public void customerMenu(Statement statement) {
        try {
            menuTools.customerMenuOptions();
            int answer = userInput.nextInt();
            switch (answer) {
                case 1 -> customerService.viewCustomer(customerList, menuTools);
                case 2 -> customerService.updateCustomer(statement, customerList, userInput);
                case 3 -> customerService.createCustomer(statement, customerList);
                case 4 -> customerService.deleteCustomer(statement, customerList, menuTools);
                case 0 -> runMenu(statement);
            }
        } catch (SQLException sqlEx) {
            System.out.println("Error in Customer maim menu: " + sqlEx);
        }
        continueButton(statement);
    }

    public void rentalMenu(Statement statement) {
        menuTools.rentalMenuOptions();
        int answer = userInput.nextInt();
        try {
            switch (answer) {
                case 1 -> rentalService.createRentalContract(rentalList, carList, customerList, statement);
                case 2 -> rentalService.viewRentals(rentalList, menuTools);
                case 3 -> rentalService.updateRentalContracts(statement, rentalList, userInput, carList);
                case 4 -> rentalService.deleteRentalContract(statement, rentalList, userInput);
                case 0 -> runMenu(statement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       continueButton(statement);
    }

    public void continueButton(Statement statement) {
        menuTools.customizedButton(15, 1, ">1< continue..");
        System.out.print(" ");
        int start = userInput.nextInt();
        if (start != 0) {
            runMenu(statement);
        }
    }
}