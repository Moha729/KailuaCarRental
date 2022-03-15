package controller;

import UI.MoTools;
import db.DBManager;
import models.Car;
import models.Customer;
import service.CarService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleController {

    final Scanner userInput = new Scanner(System.in);//Scanner
    final MoTools tools = new MoTools(); //buttons
    Connection connection = DBManager.getConnection();//returns connection
    ArrayList<Car> carList = new ArrayList<>();//All cars are here
    ArrayList<Customer> customerList = new ArrayList<>(); // All customers are here
    CarService carService = new CarService();//Service class

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
        }
        carService.populateCars(statement, carList);

        runMenu(statement);
    }

    public void runMenu(Statement statement) throws SQLException {
        tools.customizedButton(120, 7, "Welcome to Kailua car rental");

        System.out.print(tools.doubleButton(">1< Cars", ">2< Customers"));
        System.out.print(tools.doubleButton(">3< New rental", ">4< Active rentals"));

        int mainSwitch = userInput.nextInt();

        switch (mainSwitch) {

            case 1 -> runCarMenu(statement);

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

    private void CustomerMenu() {

    }

    private void RentalMenu() {
    }
}
