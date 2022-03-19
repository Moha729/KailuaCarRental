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
        try {
            Statement statement;
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
        tools.menuOptions();
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

    public void runCarMenu(Statement statement) {
        try {
            tools.carMenuOptions();

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
        try {
           tools.customerMenuOptions();

            int answer = userInput.nextInt();
            switch (answer) {
                case 1 -> customerService.viewCustomer(customerList, tools);
                case 2 -> customerService.updateCustomer(statement, customerList, userInput);
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
        tools.rentalMenuOptions();

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


    public void closeProgram(Statement statement)  {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Closing the application...");
            Thread.sleep(1000);
            tools.customizedButton(120, 1,"System closed");
            System.exit(0);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

    }

    public void continueButton(Statement statement) {
        tools.customizedButton(15, 1, ">1< continue..");
        System.out.print(" ");
        int start = userInput.nextInt();
        if (start != 0) {
            runMenu(statement);
        }
    }
}




