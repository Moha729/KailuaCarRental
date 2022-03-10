package controller;

import databaseHandler.DBManager;
import models.Car;
import models.Family;
import models.Luxury;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class RunApplication {

     final Scanner userInput = new Scanner(System.in);
     Connection connection = DBManager.getConnection();//returns connection
     ArrayList<Car> carList = new ArrayList<>();

     boolean running = true;

     public void run() throws SQLException {
        Statement statement = connection.createStatement();
        while (running) {

            System.out.println("Enter 1 to add a car. 2 to ");
            switch (userInput.nextInt()) {
                  case 1 -> addCarToDatabase(statement);
                  case 2 -> show(statement);
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

    public void addCarToDatabase(Statement statement) {

        try {
            System.out.println("Enter registration number");
            String registrationNumber = userInput.next();

            System.out.println("Enter brand ");
            String brand = userInput.next();

            System.out.println("Enter model");
            String model = userInput.next();

            System.out.println("Enter registration date");
            String registrationDate = userInput.next();

            System.out.println("Enter km driven");
            int kmDriven = userInput.nextInt();

            System.out.println("Enter ccm");
            boolean ccm = userInput.nextBoolean();

            System.out.println("Enter gear");
            boolean gear = userInput.nextBoolean();

            System.out.println("Enter cruisecontrol");
            boolean cruiseControl = userInput.nextBoolean();

            System.out.println("Enter leather seats");
            boolean leatherSeats = userInput.nextBoolean();

            Luxury luxuryCar = new Luxury(registrationNumber,brand,model,registrationDate,kmDriven,ccm,gear,cruiseControl,leatherSeats);
            carList.add(luxuryCar);

            boolean manualGear = userInput.nextBoolean();
            boolean airCondition = userInput.nextBoolean();
            boolean cruise_control1 = userInput.nextBoolean();
            boolean sevenSeatsOrMore = userInput.nextBoolean();
            Family familyCar = new Family(registrationNumber,brand,model,registrationDate,kmDriven,manualGear,airCondition,
                    cruise_control1,sevenSeatsOrMore);
            carList.add(familyCar);

            statement.execute("INSERT INTO cars " + "(registration_number,brand,model, registration_date, kmDriven)" + "" +
                    "VALUES('"
                         + luxuryCar.getRegistrationNumber()   + "','"
                         + luxuryCar.getBrand()                +  "','"
                         + luxuryCar.getModel()                + "','"
                         + luxuryCar.getRegistrationDate()     + "','"
                         + luxuryCar.getKmDriven()             + "')");

            statement.execute("INSERT INTO  luxury " + "(registration_number, ccm,gear,cruise_control, leather_seats)" + "" +
                    "VALUES('"
                    + luxuryCar.getRegistrationNumber() + "','"
                    + luxuryCar.isOver3000CCM()         + "','"
                    + luxuryCar.isAutomaticGear()       +  "','"
                    + luxuryCar.isCruiseControl()       + "','"
                    + luxuryCar.isLeatherSeats()        + "')");

            statement.execute("INSERT INTO  family " + "(registration_number, manualGear ,airCondition , cruise_control1, sevenSeatsOrMore)" + "" +
                    "VALUES('"
                    + familyCar.getRegistrationNumber() + "','"
                    + familyCar.isManualGear() + "','"
                    + familyCar.isAirCondition()         + "','"
                    + familyCar.isCruiseControl()       +  "','"
                    + familyCar.isSevenSeatsOrMore()       + "',')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void show(Statement statement) throws SQLException {
         String name = userInput.next();

        statement.execute("SELECT * FROM cars WHERE registration_number = '"+ name+"'");
    }
}
