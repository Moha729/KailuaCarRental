package repository;

import UI.UITools;
import models.Car;
import models.Family;


import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class FamilyRepository {
    DBCarRepo dbCarRepo = new DBCarRepo();


    public Family createFamilyCar(Statement statement, String  reg,
                                  String br, String mo, String regDate, int kmDr, UITools tools){

        boolean manualGear;
        boolean airCondition;
        boolean cruiseControl;
        boolean sevenSeatsOrMore;

        if (tools.returnStringInfo(60, 1, "does it have manual gear?").equalsIgnoreCase("yes")){
            manualGear = true;
        }else {
            manualGear = false;
        }
        if (tools.returnStringInfo(60, 1, "does it have air condition?").equalsIgnoreCase("yes")){
            airCondition = true;
        }else {
            airCondition = false;
        }
        if (tools.returnStringInfo(60, 1, "does it have cruise control?").equalsIgnoreCase("yes")){
            cruiseControl = true;
        }else {
            cruiseControl = false;
        }
        if (tools.returnStringInfo(60, 1, "does it have more then 7 seats?").equalsIgnoreCase("yes")){
            sevenSeatsOrMore = true;
        }else {
            sevenSeatsOrMore = false;
        }

        Family familyCar = new Family(reg, br , mo, regDate,kmDr,manualGear,airCondition,
                cruiseControl,sevenSeatsOrMore);

        addFamilyCarToDataBase(familyCar);

        return familyCar;
    }


    public void populateFamilyToArrayList(ArrayList<Car> carList) {
            dbCarRepo.populateFamilyCarToArraylist(carList);
    }

    public void addFamilyCarToDataBase(Family familyCar){
            dbCarRepo.addFamilyCarToDB(familyCar);
    }

    public void viewFamilyCars(ArrayList<Car> carList, UITools tools){
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

    public void updateFamilyCar(Statement statement,Scanner userInput, String answer, Family car) {

        System.out.println("what do you want to update?\n" +
                "1 for regNumb\n2 for brand\n3 for model\n4 for regDate\n5 for kmDriven" +
                "\n6 for manualGear\n7 for aircondition\n8 for cruiseControl\n9 for sevenSeatsPlus");

        int ans = userInput.nextInt();

        boolean check = false;
        String newValue = null;
        String dbColumn = null;

        switch (ans) {

            case 1:
                System.out.println("Not valid try again");
                break;
            case 2:
                check = true;
                System.out.println("Enter new brand");
                newValue = userInput.next();
                dbColumn = "brand";
                car.setBrand(newValue);
                break;
            case 3:
                check = true;
                System.out.println("Enter new model");
                newValue = userInput.next();
                dbColumn = "model";
                car.setModel(newValue);
                break;
            case 4:
                check = true;
                System.out.println("Enter new registration date");
                newValue = userInput.next();
                dbColumn = "registration_date";
                car.setRegistrationDate(newValue);
                break;
            case 5:
                check = true;
                System.out.println("Enter new km driven");
                int newKm = userInput.nextInt();
                newValue = String.valueOf(newKm);
                dbColumn = "km_driven";
                car.setKmDriven(newKm);
                break;
            case 6:
                check = false;
                System.out.println("Enter new gear type - does it have manual gear?");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")) {
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                dbColumn = "manual_gear";
                car.setManualGear(Boolean.parseBoolean(newValue));
                break;
            case 7:
                check = false;
                System.out.println("Enter new air condition status - does it have air condition?");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")) {
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                dbColumn = "air_condition";
                car.setAirCondition(Boolean.parseBoolean(newValue));
                break;
            case 8:
                check = false;
                System.out.println("Enter new cruise control status - does it have cruise control?");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")) {
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                dbColumn = "cruise_control";
                car.setCruiseControl(Boolean.parseBoolean(newValue));
                break;
            case 9:
                check = false;
                System.out.println("Enter new info, does it have more than 7 seats");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")) {
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                dbColumn = "seven_seats_or_more";
                car.setSevenSeatsOrMore(Boolean.parseBoolean(newValue));
                break;
        }
        if (check == true) {
            dbCarRepo.updateCar(dbColumn,newValue,answer);
        }
        if (check == false) {
            dbCarRepo.updateAllCar(dbColumn,newValue,answer, "family_cars");
        }
    }
    public void deleteFamilyCar(ArrayList<Car> carList, String answer, UITools tools){
        dbCarRepo.deleteAllCar(answer, "family_cars");
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(answer)) {
                carList.remove(carList.get(i));
                System.out.println("Car " + answer + " is deleted");
            }
        }
    }
}