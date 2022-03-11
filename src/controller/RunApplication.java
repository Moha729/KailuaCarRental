package controller;

import UI.MenuHandler;
import databaseHandler.DBManager;
import models.Car;
import models.Luxury;
import service.CarService;
import testing.Testing;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class RunApplication {

    final Scanner userInput = new Scanner(System.in);//Scanner
    Connection connection = DBManager.getConnection();//returns connection
    ArrayList<Car> carList = new ArrayList<>();//All cars are here
    CarService carService = new CarService();//Service class
    MenuHandler menuHandler = new MenuHandler();//UI class
    Testing testing = new Testing();
    boolean running = true;
    Car car;

    public void run() throws SQLException {
        Statement statement = connection.createStatement();
        testing.populateArrayList(statement,carList,car);
        while (running) {

            menuHandler.getWelcomeScreen("Welcome to Kailua car rental");//Runs welcome box

            menuHandler.getMainOptions(">1< Available cars", ">2< Customers",
                    ">3< New rental", ">4< Active rentals");//main menu

            switch (userInput.nextInt()) {
                case 1 -> runCarMenu(statement);
//                case 2 -> viewCars(statement);
//                case 3 -> System.out.println(carList);
                case 4 -> testing.join(statement);
                case 5 -> testing.update(statement,carList);
                case 6 -> carService.createCar(statement, userInput, carList);
//                case 7 ->
                default -> {
                    if (running) {
                        System.out.println("Enter a valid number");
                    }
                }
            }
        }
    }

    public void runCarMenu(Statement statement) {
        System.out.println();
        menuHandler.tools.customizedButton(120, 1, "Cars menu");

        menuHandler.getMainOptions(">1< See cars", ">2< Update car", ">3< New car", ">4< \"Delete car\"");
//        carService.createCar(statement, userInput, carList);
        switch (userInput.nextInt()) {

            case 1 -> System.out.println(carList); //view cars
//            case 2 -> //change information for car
            case 3 -> carService.createCar(statement, userInput, carList);
            case 4 -> testing.join(statement);


        }

//        carService.createCar(statement, userInput, carList);
    }


    private void runCustomerMenu() {
    }

    private void runRentalMenu() {
    }


}
