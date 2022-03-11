package service;

import UI.MoTools;
import models.Car;
import models.Family;
import models.Luxury;
import models.Sport;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.Statement;

public class CarService {

    MoTools tools = new MoTools();


    public void createCar(Statement statement, Scanner userInput, ArrayList<Car> carList) {

        try {

            int chooseType = tools.returnIntInfo(80,1, "Which type is it? - Enter 1 for Luxury - Enter 2 for Sport - Enter 3 for Family");

            while (chooseType != 1 || chooseType != 2 || chooseType != 3){
                tools.customizedButton(60, 1, "Try again!");
                tools.customizedButton(80, 1, "Which type is it? - Enter 1 for Luxury - Enter 2 for Sport - Enter 3 for Family");
            }

            String registrationNumber = tools.returnStringInfo(50,1, "Enter registration number");

            String brand = tools.returnStringInfo(50,1, "Enter brand");

            String model =  tools.returnStringInfo(50,1, "Enter model");

            String registrationDate  = tools.returnStringInfo(50,1, "Enter date");

            int kmDriven = tools.returnIntInfo(50,1, "Enter registration number");




            if(chooseType == 1){

                Luxury luxuryCar = createLuxury(statement, userInput, carList, registrationNumber, brand, model,
                        registrationDate, kmDriven);


            } else if(userInput.nextInt() == 2){
                createSportsCar(statement, userInput, carList);

            } else if(userInput.nextInt() == 3){

                Family familyCar = createFamilyCar(statement, userInput, carList, registrationNumber, brand, model,
                        registrationDate, kmDriven);

            } else { tools.customizedButton(60, 1, "Try again!");

            }

        } catch (SQLException e) {
            tools.customizedButton(60, 1, e.getMessage());
        }

    }

    private Luxury createLuxury(Statement statement, Scanner userInput, ArrayList<Car> carList, String  reg, String br, String mo,
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

        addLuxuryCarToDB(luxuryCar, statement);

        return luxuryCar;
    }
    private void addLuxuryCarToDB(Luxury luxuryCar, Statement statement) throws SQLException {

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

    private Family createFamilyCar(Statement statement, Scanner userInput, ArrayList<Car> carList, String  reg, String br, String mo,
                                  String regDate, int kmDr) throws SQLException {

        boolean manualGear = userInput.nextBoolean();
        boolean airCondition = userInput.nextBoolean();
        boolean cruise_control1 = userInput.nextBoolean();
        boolean sevenSeatsOrMore = userInput.nextBoolean();

        Family familyCar = new Family(reg, br , mo, regDate,kmDr,manualGear,airCondition,
                cruise_control1,sevenSeatsOrMore);
        carList.add(familyCar);

        addFamilyCarToDB(familyCar, statement);

        return familyCar;
    }

    private void addFamilyCarToDB(Family familyCar, Statement statement) throws SQLException {
        statement.execute("INSERT INTO  family " + "(registration_number, manualGear ,airCondition , cruise_control1, sevenSeatsOrMore)" + "" +
                "VALUES('"
                + familyCar.getRegistrationNumber() + "','"
                + familyCar.isManualGear() + "','"
                + familyCar.isAirCondition()         + "','"
                + familyCar.isCruiseControl()       +  "','"
                + familyCar.isSevenSeatsOrMore()       + "',')");
    }





    private void createSportsCar(Statement statement, Scanner userInput, ArrayList<Car> carList){

    }

    private void addSportsCarToDB(Sport sportsCar, Statement statement){}



    /*public void show(Statement statement) throws SQLException {
        String name = userInput.next();

        statement.execute("SELECT * FROM cars WHERE registration_number = '"+ name+"'");
    }*/
}
