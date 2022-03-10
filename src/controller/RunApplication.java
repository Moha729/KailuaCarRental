package controller;

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

     final Scanner userInput = new Scanner(System.in);
     Connection connection = DBManager.getConnection();//returns connection
     ArrayList<Car> carList = new ArrayList<>();
     CarService carService = new CarService();
     boolean running = true;

     public void run() throws SQLException {
        Statement statement = connection.createStatement();
        while (running) {

            System.out.println("Enter 1 to add a car. 2 to ");
            switch (userInput.nextInt()) {
                  case 1 -> carService.addCarToDatabase(statement, userInput, carList);
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

}
