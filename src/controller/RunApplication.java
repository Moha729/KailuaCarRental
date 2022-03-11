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
    Car readCars;
    boolean running = true;

    public void run() throws SQLException {
        Statement statement = connection.createStatement();
        readCarsFromDatabase(statement);
        while (running) {

            menuHandler.getWelcomeScreen("Welcome to Kailua car rental");//Runs welcome box

            menuHandler.getMainOptions(">1< Available cars", ">2< Customers",
                            ">3< New rental",">4< Active rentals");//main menu

            switch (userInput.nextInt()) {
                case 1 -> runCarMenu(statement);
//                case 2 -> viewCars(statement);
                case 3 -> System.out.println(carList);
                case 4 -> join(statement);
                case 5 -> update(statement);
                case 6 -> carService.createCar(statement,userInput,carList);
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
        carService.createCar(statement, userInput, carList);
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


        }

        }


    public void join(Statement statement){
        try {
            String sql = ("SELECT * FROM car_table INNER JOIN luxury_cars ON car_table.registration_number = luxury_cars.registration_number");
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("reg  brand   model    regdate    kmdriven    ccm   gear   cruise    leather");
            if (resultSet != null)
                while (resultSet.next()) {

                    String registration_number =   resultSet.getString("registration_number");
                    String brand =     resultSet.getString("brand");
                    String model =     resultSet.getString("model");
                    String registration_date =  resultSet.getString("registration_date");
                    int kmDriven =     resultSet.getInt("kmDriven");
                    String ccm =       resultSet.getString("ccm");
                    String gear =      resultSet.getString("gear");
                    String cruise_control =  resultSet.getString("cruise_control");
                    String leather_seats =  resultSet.getString("leather_seats");

                    System.out.println(registration_number +" / " +brand + " / "+ model + " / " + registration_date+" / "
                            +kmDriven+" / "+ ccm +" / " +gear+" / " + cruise_control+ " / " + leather_seats+" / ");

                }


            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }

    public void readCarsFromDatabase(Statement statement) { // table content
        try {
            statement.execute("SELECT * FROM car_table");
            ResultSet resultSet = statement.getResultSet();
            if (resultSet != null)
                while (resultSet.next()) {
                     readCars = new Car(
                     resultSet.getString("registration_number"),
                     resultSet.getString("brand"),
                     resultSet.getString("model"),
                     resultSet.getString("registration_date"),
                     resultSet.getInt("kmDriven"));

                    carList.add(readCars);
                }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }

    public void update(Statement statement) throws SQLException {
//        try {
//                System.out.println("Enter which registration number to be updated");
//                String existingRegistrationNumber = userInput.next();

                for (int i = 0; i<carList.size();i++){
                    System.out.println(carList.get(i));
                }
                System.out.println("Enter which registration number to be updated");
                int answer = userInput.nextInt();

                String newNumber = userInput.next();
                carList.get(answer).setRegistrationNumber(newNumber);
                String newBrand = userInput.next();
                carList.get(answer).setBrand(newBrand);
                String newModel = userInput.next();
                carList.get(answer).setModel(newModel);
                String regDate = userInput.next();
                carList.get(answer).setRegistrationDate(regDate);
                int km = userInput.nextInt();
                carList.get(answer).setKmDriven(km);

//                String a= carList.get(answer).toString();
//                System.out.println(a);

            statement.execute("UPDATE car_table  SET " +
                    "  registration_number='" + newNumber + "' , "
                    + "brand='" + newBrand + "' , "
                    + "model='" + newModel + "' , "
                    + "registration_date='" + regDate + "' , "
                    + "kmDriven ='" + km + "'");

    }

}
