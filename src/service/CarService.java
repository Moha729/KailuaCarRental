package service;

import models.Car;
import models.Family;
import models.Luxury;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.Statement;

public class CarService {


    public void addCarToDatabase(Statement statement, Scanner userInput, ArrayList<Car> carList) {

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


            System.out.println("Which type is it? - Enter 1 for Luxury - Enter 2 for Sport - Enter 3 for Family");

            if(userInput.nextInt() == 1){
                Luxury luxuryCar = addLuxury(statement, userInput, carList, registrationNumber, brand, model,
                        registrationDate, kmDriven);
                saveLuxuryToDatabase(luxuryCar, statement);
            } else if(userInput.nextInt() == 2){
                addSport(statement, userInput, carList);
            } else if(userInput.nextInt() == 3){
                Family familyCar = addFamily(statement, userInput, carList, registrationNumber, brand, model,
                        registrationDate, kmDriven);
                saveFamilyToDatabase(familyCar, statement);
            } else {
                while (!userInput.hasNextInt()) {
                    System.out.println("You have to enter a correct number");
                    System.out.println("Which type is it? - Enter 1 for Luxury - Enter 2 for Sport - Enter 3 for Family");
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private void saveFamilyToDatabase(Family familyCar, Statement statement) throws SQLException {
        statement.execute("INSERT INTO  family " + "(registration_number, manualGear ,airCondition , cruise_control1, sevenSeatsOrMore)" + "" +
                "VALUES('"
                + familyCar.getRegistrationNumber() + "','"
                + familyCar.isManualGear() + "','"
                + familyCar.isAirCondition()         + "','"
                + familyCar.isCruiseControl()       +  "','"
                + familyCar.isSevenSeatsOrMore()       + "',')");
    }

    private void saveLuxuryToDatabase(Luxury luxuryCar, Statement statement) throws SQLException {

        statement.execute("INSERT INTO cars " + "(registration_number,brand,model, registration_date, kmDriven)" + "" +
                "VALUES('"
                + luxuryCar.getRegistrationNumber()   + "','"
                + luxuryCar.getBrand()                +  "','"
                + luxuryCar.getModel()                + "','"
                + luxuryCar.getRegistrationDate()     + "','"
                + luxuryCar.getKmDriven()             + "')");

        statement.execute("INSERT INTO  luxury " + "(registration_number, ccm, gear, cruise_control, leather_seats)" + "" +
                "VALUES('"
                + luxuryCar.getRegistrationNumber() + "','"
                + luxuryCar.isOver3000CCM()         + "','"
                + luxuryCar.isAutomaticGear()       +  "','"
                + luxuryCar.isCruiseControl()       + "','"
                + luxuryCar.isLeatherSeats()        + "')");
    }

    public Luxury addLuxury(Statement statement, Scanner userInput, ArrayList<Car> carList, String  reg, String br, String mo,
                          String regDate, int kmDr) throws SQLException {

        System.out.println("Enter ccm");
        boolean ccm = userInput.nextBoolean();

        System.out.println("Enter gear");
        boolean gear = userInput.nextBoolean();

        System.out.println("Enter cruisecontrol");
        boolean cruiseControl = userInput.nextBoolean();

        System.out.println("Enter leather seats");
        boolean leatherSeats = userInput.nextBoolean();

        Luxury luxuryCar = new Luxury(reg, br, mo, regDate, kmDr, ccm, gear, cruiseControl, leatherSeats);
        carList.add(luxuryCar);

      return luxuryCar;
    }

    public void addSport(Statement statement, Scanner userInput, ArrayList<Car> carList){

    }

    public Family addFamily(Statement statement, Scanner userInput, ArrayList<Car> carList, String  reg, String br, String mo,
                            String regDate, int kmDr) throws SQLException {

        boolean manualGear = userInput.nextBoolean();
        boolean airCondition = userInput.nextBoolean();
        boolean cruise_control1 = userInput.nextBoolean();
        boolean sevenSeatsOrMore = userInput.nextBoolean();

        Family familyCar = new Family(reg, br , mo, regDate,kmDr,manualGear,airCondition,
                cruise_control1,sevenSeatsOrMore);
        carList.add(familyCar);

        return familyCar;
    }

    /*public void show(Statement statement) throws SQLException {
        String name = userInput.next();

        statement.execute("SELECT * FROM cars WHERE registration_number = '"+ name+"'");
    }*/
}
