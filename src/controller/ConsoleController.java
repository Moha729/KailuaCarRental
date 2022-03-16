package controller;

import UI.UITools;
import db.DBManager;
import models.Car;
import models.Customer;
import models.Rental;
import repository.RentalRepository;
import service.CarService;
import service.CustomerService;
import service.RentalService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleController {


    final Scanner userInput = new Scanner(System.in);//Scanner
    final UITools tools = new UITools(); //buttons
    Connection connection = DBManager.getConnection();//returns connection
    ArrayList<Car> carList = new ArrayList<>();//All cars are here
    ArrayList<Customer> customerList = new ArrayList<>(); // All customers are here
    ArrayList<Rental> rentalList = new ArrayList<>();
    CarService carService = new CarService();//Service cars
    CustomerService customerService = new CustomerService(); // Service customer
    RentalService rentalService = new RentalService(); // Service rentals
    Customer customer = new Customer();

    public void run() throws SQLException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No connection");
        }
        if (statement == null) {
            System.out.println("No Connection");
        } else {
            carService.populateCars(statement, carList);
            customerService.populateCustomerToArrayList(statement, customerList);
            rentalService.populateRentalContractsToArrayList(statement, rentalList);
            runMenu(statement);
        }
    }

    public void runMenu(Statement statement) throws SQLException {
        tools.customizedButton(120, 7, "Welcome to Kailua car rental");

        System.out.print(tools.doubleButton(">1< Cars", ">2< Customers"));
        System.out.print(tools.doubleButton(">3< Rentals", ">4< Exit"));

        int mainSwitch = userInput.nextInt();

        switch (mainSwitch) {

            case 1 -> runCarMenu(statement);
            case 2 -> customerMenu(statement);
            case 3 -> rentalMenu(statement, rentalList);
            case 4 -> System.exit(4);

            case 6 -> carService.createCar(statement, userInput, carList, tools);
            case 8 -> carService.updateCar(statement, userInput, carList, tools);
            case 0 -> System.exit(1);
            }

        int start = userInput.nextInt();
        if (start != 0) {
            runMenu(statement);
        }
    }

    public void runCarMenu(Statement statement) throws SQLException {

        try {
            System.out.println();
            tools.customizedButton(120, 3, "Cars menu");

            System.out.print(tools.doubleButton(">1< See cars", ">2< Update car"));
            System.out.print(tools.doubleButton(">3< New car", ">4< \"Delete car\""));

            int carsSwitch = userInput.nextInt();

            switch (carsSwitch) {

                case 1 -> carService.viewCars(carList, tools);
                case 2 -> carService.updateCar(statement, userInput, carList, tools);
                case 3 -> carService.createCar(statement, userInput, carList, tools);
                case 4 -> carService.delete(statement, carList, userInput, tools);
                case 0 -> runMenu(statement);

            }
        } catch (SQLException sqlEx) {
            System.out.println("Error in Cars_main_menu: " + sqlEx);
        }

        tools.customizedButton(15, 1, ">1< continue..");
        System.out.print(" ");
        int start = userInput.nextInt();
        if (start != 0) {
            runMenu(statement);
        }

    }

    public void customerMenu(Statement statement) throws SQLException {
        try {
            System.out.println();
            tools.customizedButton(120, 3, "Customer menu");

            System.out.print(tools.doubleButton(">1< See customers", ">2< Update a customer"));
            System.out.print(tools.doubleButton(">3< Create a new customer", ">4< \"Delete a customer\""));

            int customerSwitch = userInput.nextInt();

            switch (customerSwitch) {

                case 1 -> customerService.viewCustomer(statement, customerList, tools);
                case 2 -> customerService.updateCustomer(statement, customerList, userInput,customer);
                case 3 -> customerService.createCustomer(statement,customerList);
                case 4 -> customerService.deleteCustomer(statement, customerList, userInput, tools);
                case 0 -> customerMenu(statement);

            }
        } catch (SQLException sqlEx) {
            System.out.println("Error in Customer maim menu: " + sqlEx);
        }

        tools.customizedButton(15, 1, ">1< continue..");
        System.out.print(" ");
        int start = userInput.nextInt();
        if (start != 0) {
            runMenu(statement);
        }

    }

    public void rentalMenu(Statement statement, ArrayList<Rental> rentals) throws SQLException {

        RentalRepository rentalRepository = new RentalRepository();

        System.out.println();
        tools.customizedButton(120, 3, "Rentals menu");

        System.out.print(tools.doubleButton(">1< New rental", ">2< Active rentals"));
        System.out.print(tools.doubleButton(">3< Change rental", ">4< End rental"));

        int rentalSwitch = userInput.nextInt();

        switch (rentalSwitch) {

            case 1 -> rentalRepository.createRentalContract(rentalList, carList,customerList);
            case 2 -> rentalRepository.viewRentals(rentalList);
            case 3 -> rentalRepository.updateRentalContracts(statement, rentalList, userInput);
            case 4 -> rentalRepository.deleteRentalContract(statement, rentalList, userInput);
            default -> rentalMenu(statement, rentalList);
        }
    }
}
