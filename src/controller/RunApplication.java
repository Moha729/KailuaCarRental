package controller;

import UI.MenuHandler;
import databaseHandler.DBManager;
import models.Car;
import models.Family;
import models.Luxury;
import service.CarService;

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
     boolean running = true;

     public void run() throws SQLException {
        Statement statement = connection.createStatement();
        while (running) {

            menuHandler.getWelcomeScreen("Welcome to Kailua car rental");//Runs welcome box

            menuHandler.getMainOptions(">1< Available cars", ">2< Customers", ">3< New rental",
                    ">4< Active rentals");//main menu

//          System.out.println("Enter 1 to add a car. 2 to ");
            switch (userInput.nextInt()) {
                  case 1 -> runCarMenu(statement);
//                case 2 -> show(statement);
//                case 3 ->
//                case 4 ->
//                case 5 ->
//                case 6 ->
//                case 7 ->
                default -> {
                    if (running) {
                        System.out.println("Enter a valid number");
                    }
                }
            }
        }
    }

    public void runCarMenu(Statement statement){
         menuHandler.getMainOptions(">1< See cars", ">2< Update car", ">3< New car", ">4< \"Delete car\"");
        carService.addCarToDatabase(statement, userInput, carList);
    }

}
