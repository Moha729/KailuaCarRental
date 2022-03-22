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
            customerService.populateCustomerToArrayList(customerList);
            rentalService.populateRentalContractsToArrayList(rentalList, carList, customerList);

            runMenu(statement);
        } catch (SQLException e) {
            System.out.println("No connection" + e.getMessage());
        }
    }

    public void runMenu(Statement statement) {

        switch (menuTools.menuOptions("menuOptions")) {
            case 1 -> rentalMenu(statement);
            case 2 -> carMenu(statement);
            case 3 -> customerMenu(statement);
            case 4,0 -> menuTools.closeProgram(statement,connection);
        }
        if (menuTools.continueButton()) {
            runMenu(statement);
        }    }

    public void carMenu(Statement statement) {
        try {

            switch (menuTools.menuOptions("carMenuOptions")) {
                case 1 -> carService.viewCars(carList, menuTools);
                case 2 -> carService.updateCar(userInput, carList, menuTools);
                case 3 -> carService.createCar(userInput, carList, menuTools);
                case 4 -> carService.delete(carList, menuTools);
                case 0 -> {menuTools.whiteSpace(10);runMenu(statement);}
            }
        } catch (SQLException sqlEx) {
            System.out.println("Error in Cars_main_menu: " + sqlEx);
        }
        if (menuTools.continueButton()) {
            runMenu(statement);
        }    }

    public void customerMenu(Statement statement) {
        try {

            switch (menuTools.menuOptions("customerMenuOptions")) {
                case 1 -> customerService.viewCustomer(customerList, menuTools);
                case 2 -> customerService.updateCustomer(statement, customerList, userInput);
                case 3 -> customerService.createCustomer(statement, customerList);
                case 4 -> customerService.deleteCustomer(statement, customerList, menuTools);
                case 0 -> {menuTools.whiteSpace(10);runMenu(statement);}
            }
        } catch (SQLException sqlEx) {
            System.out.println("Error in Customer maim menu: " + sqlEx);
        }
        if (menuTools.continueButton()) {
            runMenu(statement);
        }    }

    public void rentalMenu(Statement statement) {

        try {
            switch (menuTools.menuOptions("rentalMenuOptions")) {
                case 1 -> rentalService.createRentalContract(rentalList, carList, customerList, statement);
                case 2 -> rentalService.viewRentals(rentalList, menuTools);
                case 3 -> rentalService.updateRentalContracts(statement, rentalList, userInput, carList);
                case 4 -> rentalService.deleteRentalContract(statement, rentalList);
                case 0 -> {menuTools.whiteSpace(10);runMenu(statement);}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (menuTools.continueButton()) {
            runMenu(statement);
        }
    }
}