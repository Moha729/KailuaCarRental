package controller;

import UI.MoTools;
import db.DBManager;
import models.Car;
import models.Customer;
import service.CarService;
import testing.Testing;

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

    Testing testing = new Testing();
    boolean running = true;

    public void run() {
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

        tools.customizedButton(120, 7, "Welcome to Kailua car rental");

        System.out.print(tools.dobbleButton(">1< Cars", ">2< Customers"));
        System.out.print(tools.dobbleButton(">3< New rental", ">4< Active rentals"));

        int mainSwitch = userInput.nextInt();

        //while (!userInput.hasNextInt()) {
       //     tools.customizedButton(40, 1, "Not valid - try again!");}


        switch (mainSwitch) {
            case 1 -> runCarMenu(statement);
//                case 5 ->
            case 6 -> carService.createCar(statement, userInput, carList);
            case 8 -> carService.updateCar(statement, userInput, carList);
            default -> {
                if (running) {
                    System.out.println("Enter a valid number");
                }
            }
        }
        int start = userInput.nextInt();
        if (start != 0) {
            run();
        }
    }


    public void runCarMenu(Statement statement) {

        try {


            System.out.println();
            tools.customizedButton(120, 1, "Cars menu");

            System.out.print(tools.dobbleButton(">1< See cars", ">2< Update car"));
            System.out.print(tools.dobbleButton(">3< New car", ">4< \"Delete car\""));

            int carsSwitch = userInput.nextInt();

            //while (!userInput.hasNextInt()) {
              //  tools.customizedButton(40, 1, "Not valid - try again!");
            //}
            switch (carsSwitch) {

                case 1 -> System.out.println(carList); //view cars
                case 2 -> carService.updateCar(statement, userInput, carList);
                case 3 -> carService.createCar(statement, userInput, carList);
                case 4 -> carService.delete(statement, carList, userInput);


            }
        } catch (SQLException sqlEx){
            System.out.println("Error in Cars_main_menu: " + sqlEx);
        }
        int start = userInput.nextInt();
        if (start != 0){
            run();
        }

    }

    private void CustomerMenu() {

    }

    private void RentalMenu() {
    }


}
