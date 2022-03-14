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
    SportService sportService = new SportService();
    FamilyService familyService = new FamilyService();


    public void createCar(Statement statement, Scanner userInput, ArrayList<Car> carList) {

        try {

            int chooseType = tools.returnIntInfo(100,1, "Which type is it? - Enter 1 for Luxury - Enter 2 for Sport - Enter 3 for Family");

            /*while (chooseType != 1 || chooseType != 2 || chooseType != 3){
                tools.customizedButton(60, 1, "Try again!");
                tools.customizedButton(80, 1, "Which type is it? - Enter 1 for Luxury - Enter 2 for Sport - Enter 3 for Family");
            }*/ //Robust programming not ready yet

            String registrationNumber = tools.returnStringInfo(50,1, "Enter registration number");

            String brand = tools.returnStringInfo(50,1, "Enter brand");

            String model =  tools.returnStringInfo(50,1, "Enter model");

            String registrationDate  = tools.returnStringInfo(50,1, "Enter date");

            int kmDriven = tools.returnIntInfo(50,1, "Enter km driven");




            if(chooseType == 1){

                Luxury luxuryCar = createLuxury(statement, carList, userInput, registrationNumber, brand, model,
                        registrationDate, kmDriven);
                carList.add(luxuryCar);


            } else if(chooseType == 2){

                Sport sportsCar = sportService.createSportsCar(statement, userInput, carList, registrationNumber, brand, model,
                        registrationDate, kmDriven);
                carList.add(sportsCar);

            } else if(chooseType == 3){

                Family familyCar = familyService.createFamilyCar(statement, userInput, carList, registrationNumber, brand, model,
                        registrationDate, kmDriven, tools);

            } else { tools.customizedButton(60, 1, "Try again!");

            }

        } catch (SQLException e) {
            tools.customizedButton(60, 1, e.getMessage());
        }

    }

    private Luxury createLuxury(Statement statement, ArrayList<Car> carList, Scanner userInput, String  reg, String br, String mo,
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

        addLuxuryCarToDB(luxuryCar, statement);

        return luxuryCar;
    }
    private void addLuxuryCarToDB(Luxury luxuryCar, Statement statement) throws SQLException {

        statement.execute("INSERT INTO car_table " + "(registration_number,brand,model, registration_date, km_driven)" + "" +
                "VALUES('"
                + luxuryCar.getRegistrationNumber()   + "','"
                + luxuryCar.getBrand()                +  "','"
                + luxuryCar.getModel()                + "','"
                + luxuryCar.getRegistrationDate()     + "','"
                + luxuryCar.getKmDriven()             + "')");

        statement.execute("INSERT INTO  luxury_cars " + "(registration_number, ccm, automatic_gear, cruise_control, leather_seats)" + "" +
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
        statement.execute("INSERT INTO  family_cars " + "(registration_number, manualGear ,air_aondition , cruise_control1, seven_seats_or_more)" + "" +
                "VALUES('"
                + familyCar.getRegistrationNumber() + "','"
                + familyCar.isManualGear() + "','"
                + familyCar.isAirCondition()         + "','"
                + familyCar.isCruiseControl()       +  "','"
                + familyCar.isSevenSeatsOrMore()       + "')");
    }

    public void updateCar(Statement statement, Scanner userInput, ArrayList<Car> carList){
        System.out.println("1 luxury 2 sport 3 family");
        switch (userInput.nextInt()){
            case 3 -> familyService.updateFamilyCar(statement, carList, userInput);
        }

    }
}
