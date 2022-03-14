package service;

import UI.MoTools;
import models.Car;
import models.Family;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class FamilyService {


    public Family createFamilyCar(Statement statement, Scanner userInput, ArrayList<Car> carList, String  reg,
                                  String br, String mo, String regDate, int kmDr, MoTools tools){

        boolean manualGear;
        boolean airCondition;
        boolean cruiseControl;
        boolean sevenSeatsOrMore;

        if (tools.returnStringInfo(50, 1, "does it have manual gear?").equalsIgnoreCase("yes")){
            manualGear = true;
        }else {
            manualGear = false;
        }
        if (tools.returnStringInfo(50, 1, "does it have air condition?").equalsIgnoreCase("yes")){
            airCondition = true;
        }else {
            airCondition = false;
        }
        if (tools.returnStringInfo(50, 1, "does it have cruise control?").equalsIgnoreCase("yes")){
            cruiseControl = true;
        }else {
            cruiseControl = false;
        }
        if (tools.returnStringInfo(50, 1, "does it have more then 7 seats?").equalsIgnoreCase("yes")){
            sevenSeatsOrMore = true;
        }else {
            sevenSeatsOrMore = false;
        }

        Family familyCar = new Family(reg, br , mo, regDate,kmDr,manualGear,airCondition,
                cruiseControl,sevenSeatsOrMore);

        carList.add(familyCar);

        addFamilyCarToDataBase(familyCar, statement);


        return familyCar;


    }

    private void addFamilyCarToDataBase(Family familyCar, Statement statement){
        try {
            statement.execute("INSERT INTO car_table " + "(registration_number,brand,model, registration_date, km_driven)" + "" +
                    "VALUES('"
                    + familyCar.getRegistrationNumber()   + "','"
                    + familyCar.getBrand()                +  "','"
                    + familyCar.getModel()                + "','"
                    + familyCar.getRegistrationDate()     + "','"
                    + familyCar.getKmDriven()             + "')");

            statement.execute("INSERT INTO  family_cars " + "(registration_number, manual_gear ,air_condition , cruise_control, seven_seats_or_more)" + "" +
                    "VALUES('"
                    + familyCar.getRegistrationNumber() + "','"
                    + familyCar.isManualGear() + "','"
                    + familyCar.isAirCondition()         + "','"
                    + familyCar.isCruiseControl()       +  "','"
                    + familyCar.isSevenSeatsOrMore()       + "')");

        }catch (SQLException sqlException){
            System.out.println("Error adding family to database: " + sqlException);
            System.exit(1);
        }

    }

    public void viewFamilyCars(ArrayList<Car> carList, MoTools tools){
        System.out.println();
        tools.customizedButton(50,1, "Family");

        tools.margeTop(70);
        System.out.printf("\n| %-14s %-14s %-12s %-12s %-12s %-10s %-10s %-13s %-13s |\n",
                "RegNumb", "Brand", "Model", "RegDate", "kmdriven", "manu-gear", "Air-Cond.", "CruiseContr.", "Seats_>7");
        tools.margeTop(120);

        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getClass().getSimpleName().equalsIgnoreCase("family")) {
                System.out.println("\n" + carList.get(i).toString());
                tools.margeTop(120);
            }
        }

        System.out.println();

    }


    public void updateFamilyCar(Statement statement, ArrayList<Car> carList, Scanner userInput){

        for (int i = 0; i < carList.size(); i++) {
            System.out.println(carList.get(i));
        }

        System.out.println("Enter which registration number to be updated");
        String answer = userInput.next();

        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(answer))
                System.out.println(carList.get(i));
        }
        System.out.println("what do you want to update?\n" +
                "1 for regNumb\n2 for brand\n3 for model\n4 for regDate\n5 for kmDriven" +
                "\n6 for manualGear\n7 for aircondition\n 8 for cruiseControl\n9 for sevenSeatsPlus");

        int ans = userInput.nextInt();

        String statmentService = userInput.next();
        statmentService = "cruise_control";
        String cruise = userInput.next();

        String newValue = null;
        String newVariable = null;

        switch (ans){

            case 1 :
                System.out.println("Enter new registration number");
                newValue = userInput.next();
                newVariable = "registration_number";
                break;
            case 2 :
                System.out.println("Enter new brand");
                newValue =  userInput.next();
                newVariable = "brand";
                break;
        }
        /*System.out.println("Enter new registration number");
        String newNumber = userInput.next();
        System.out.println("Enter new brand");
        String newBrand =  userInput.next();

        System.out.println("Enter new model");
        String newModel = userInput.next();

        System.out.println("Enter new registration date");
        String regDate = userInput.next();

        System.out.println("Enter new km");
        int km = userInput.nextInt();

        System.out.println("Enter new ccm");
        String ccm = userInput.next();
        System.out.println("Enter if automatic gear");
        String automatic = userInput.next();
        System.out.println("Enter if cruise control ");
        //String cruise = userInput.next();
        System.out.println("Enter if leather seats");
        String leather = userInput.next();*/


        try {
            statement.execute("UPDATE car_table SET " +
                    newVariable + " = '" + newValue + "' " +
                    "WHERE registration_number ='" + answer + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update car table");
        }

        try {
            statement.execute("UPDATE family_cars SET " +
                    newVariable + " = '" + newValue + "' " +
                    "WHERE registration_number ='" + answer + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update family table");
        }



        //statement.close();

    }
}
