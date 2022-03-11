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

            menuHandler.getMainOptions(">1< Available cars", ">2< Customers",
                    ">3< New rental", ">4< Active rentals");//main menu

            switch (userInput.nextInt()) {
                case 1 -> runCarMenu(statement);
                case 2 -> runCustomerMenu();
                case 3 -> runRentalMenu();
//                case 4 -> //view active rentals

                default -> {
                    if (running) {
                        menuHandler.tools.customizedButton(50, 1, "Enter a valid number");
                    }
                }
            }
        }
    }

    private void runCarMenu(Statement statement) {
        System.out.println();
        menuHandler.tools.customizedButton(120, 1, "Cars menu");

        menuHandler.getMainOptions(">1< See cars", ">2< Update car", ">3< New car", ">4< \"Delete car\"");


        switch (userInput.nextInt()) {

//            case 1 -> viewCars(statement); //view cars
//            case 2 -> //change information for car
            case 3 -> carService.createCar(statement, userInput, carList);
//            case 4 -> //deletecar


        }
    }

    private void runCustomerMenu(){}

    private void runRentalMenu(){}


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
                            resultSet.getString("registration_number") +
                                    resultSet.getString("brand") +
                                    resultSet.getString("model") +
                                    resultSet.getString("registration_date") +
                                    resultSet.getInt("kmDriven"));
                    //Add disse informationer til Arraylisten, sådan så man læser objecterne fra Arraylisten
                }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }
}
