package controller;

import UI.MenuHandler;
import databaseHandler.DBManager;
import models.Car;
import service.CarService;

import java.sql.Connection;
import java.sql.ResultSet;
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
                case 2 -> viewCars(statement);
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

    public void runCarMenu(Statement statement) {

        menuHandler.getMainOptions(">1< See cars", ">2< Update car", ">3< New car", ">4< \"Delete car\"");
        carService.addCarToDatabase(statement, userInput, carList);
    }


    public void updateCar(Statement statement) {

//      String table = userInput.next();

        switch (userInput.nextInt()) {
            case 1:
                System.out.println("Enter new registration number");
                String registration_number = userInput.next();
                break;

            case 2:
                System.out.println("Enter new brand");
                String brand = userInput.next();
                break;

            case 3:
                System.out.println("Enter new model");
                String model = userInput.next();
                break;

            case 4:

                System.out.println("Enter new registration_date");
                String registration_date = userInput.next();
                break;

            case 5:
                System.out.println("Enter new km driven");
                int kmDriven = userInput.nextInt();
                break;


//            try {
////                System.out.println("Enter which registration number to be updated");
////                String existingRegistrationNumber = userInput.next();
//                statement.execute("UPDATE cars  SET " +
//                        "  registration_number='" + registration_number + "' , "
//                        + "brand='" + brand + "' , "
//                        + "model='" + model + "' , "
//                        + "registration_date='" + registration_date + "' , "
//                        + "kmDriven ='" + kmDriven + "' "
//                        + "WHERE registration_number ='" + existingRegistrationNumber + "'");
//            } catch (SQLException e) {
//                System.out.println(e.getMessage() + "\n");
//            }
//        }

        }
    }

    public void viewCars(Statement statement) { // table content
        try {
            statement.execute("SELECT * FROM cars");
            ResultSet resultSet = statement.getResultSet();
            if (resultSet != null)
                while (resultSet.next()) {
                    System.out.println(
                            resultSet.getString("registration_number") + " "+
                                    resultSet.getString("brand") + " "+
                                    resultSet.getString("model") + " "+
                                    resultSet.getString("registration_date") + " "+
                                    resultSet.getInt("kmDriven"));
                }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }
}
