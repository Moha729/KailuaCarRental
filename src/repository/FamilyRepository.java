package repository;

import UI.MoTools;
import models.Car;
import models.Family;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class FamilyRepository {

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


        addFamilyCarToDataBase(familyCar, statement);

        System.out.println("pres 1 to continue");

        return familyCar;

    }


    public void populateFamilyToArrayList(Statement statement, ArrayList<Car> carList) { // table content
        try {

            String sql = ("SELECT * FROM car_table INNER JOIN family_cars ON car_table.registration_number = family_cars.registration_number");
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null)
                while (resultSet.next()) {
                    Family family  = new Family(
                            resultSet.getString("registration_number"),
                            resultSet.getString("brand"),
                            resultSet.getString("model"),
                            resultSet.getString("registration_date"),
                            resultSet.getInt("km_driven"),
                            resultSet.getBoolean("manual_gear"),
                            resultSet.getBoolean("air_condition"),
                            resultSet.getBoolean("cruise_control"),
                            resultSet.getBoolean("seven_seats_or_more"));
                    carList.add(family);
                }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }

// hello
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
                "RegNumb", "Brand", "Model", "RegDate", "kmdriven", "manu-gear", "Air-Cond.", "CruiseContr.", ">7Seats");
        tools.margeTop(120);

        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getClass().getSimpleName().equals("Family")) {
                System.out.println("\n" + carList.get(i).toString());
                tools.margeTop(120);
            }
        }
        System.out.println();
    }

    public void updateFamilyCar(Statement statement, ArrayList<Car> carList, Scanner userInput, String regNum,
    Car car) {

        /*/for (int i = 0; i < carList.size(); i++) {System.out.println(carList.get(i));}
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(answer))
                System.out.println(carList.get(i));
        }String statementService = userInput.next();
        statementService = "cruise_control";
        String cruise = userInput.next();
        //System.out.println("Enter which registration number to be updated");*/
        String answer = regNum;


        System.out.println("what do you want to update?\n" +
                "1 for regNumb\n2 for brand\n3 for model\n4 for regDate\n5 for kmDriven" +
                "\n6 for manualGear\n7 for aircondition\n 8 for cruiseControl\n9 for sevenSeatsPlus");

        int ans = userInput.nextInt();

        boolean extention = true;
        String newValue = null;
        String newVariable = null;

        switch (ans) {

            case 1:
                System.out.println("Enter new registration number");
                newValue = userInput.next();
                newVariable = "registration_number";
                car.setRegistrationNumber(newValue);
                break;
            case 2:
                System.out.println("Enter new brand");
                newValue = userInput.next();
                newVariable = "brand";
                car.setBrand(newValue);
                break;
            case 3:
                System.out.println("Enter new model");
                newValue = userInput.next();
                newVariable = "model";
                car.setModel(newValue);
                break;
            case 4:
                System.out.println("Enter new registration date");
                newValue = userInput.next();
                newVariable = "registration_date";
                car.setRegistrationDate(newValue);
                break;
            case 5:
                System.out.println("Enter new km driven");
                int newKm = userInput.nextInt();
                newValue = String.valueOf(newKm);
                newVariable = "km_driven";
                car.setKmDriven(newKm);
                break;
            case 6:
                extention = false;
                System.out.println("Enter new gear type - does it have manual gear?");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")) {
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                newVariable = "manual_gear";
                break;
            case 7:
                extention = false;
                System.out.println("Enter new air condition status - does it have air condition?");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")) {
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                newVariable = "air_condition";
                break;
            case 8:
                extention = false;
                System.out.println("Enter new cruise control status - does it have cruise control?");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")) {
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                newVariable = "cruise_control";
                break;
            case 9:
                extention = false;
                System.out.println("Enter new info, does it have more than 7 seats");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")) {
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                newVariable = "seven_seats_or_more";
                break;
        }

        try {
            statement.execute("UPDATE car_table SET " +
                    newVariable + " = '" + newValue + "' " +
                    "WHERE registration_number ='" + answer + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update car table");
        }

        if (extention == false) {
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


}
