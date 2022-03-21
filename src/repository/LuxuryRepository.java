package repository;

import UI.UITools;
import models.Car;
import models.Luxury;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class LuxuryRepository {

    DBCarRepo dbCarRepo = new DBCarRepo();


    public Luxury createLuxury(Statement statement, String  reg, String br, String mo,
                               String regDate, int kmDr, UITools tools)  {
        boolean ccm;
        boolean gear;
        boolean cruiseControl;
        boolean leatherSeats;

        if (tools.returnStringInfo(60, 1, "does it have over 3000CCM?").equalsIgnoreCase("yes")){
            ccm = true;
        }else {
            ccm = false;
        }
        if (tools.returnStringInfo(60, 1, "does it have automatic gear?").equalsIgnoreCase("yes")){
            gear = true;
        }else {
            gear = false;
        }
        if (tools.returnStringInfo(60, 1, "does it have cruise control?").equalsIgnoreCase("yes")){
            cruiseControl = true;
        }else {
            cruiseControl = false;
        }
        if (tools.returnStringInfo(60, 1, "does it have leather seats?").equalsIgnoreCase("yes")){
            leatherSeats = true;
        }else {
            leatherSeats = false;
        }

        Luxury luxuryCar = new Luxury(reg, br, mo, regDate, kmDr, ccm, gear, cruiseControl, leatherSeats);

        addLuxuryCarToDB(luxuryCar, statement);

        return luxuryCar;
    }

    public void populateLuxuryToArrayList(Statement statement,ArrayList<Car> carList) {
     dbCarRepo.populateLuxuryCarToDB(statement,carList);
    }
    public void addLuxuryCarToDB(Luxury luxuryCar, Statement statement) {
        dbCarRepo.addLuxuryCarToDB(luxuryCar,statement);
    }

    public void viewLuxuryCars(ArrayList<Car> carList, UITools tools){
        System.out.println();
        tools.customizedButton(50,1, "Luxury");

        tools.margeTop(70);
        System.out.printf("\n| %-14s %-14s %-12s %-12s %-12s %-10s %-10s %-13s %-13s |\n",
                "RegNumb", "Brand", "Model", "RegDate", "kmdriven", ">3000CCM", "Auto-gear", "CruiseContr.", "LeatherSeats");
        tools.margeTop(120);

        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getClass().getSimpleName().equals("Luxury")) {
                System.out.println("\n" + carList.get(i).toString());
                tools.margeTop(120);
            }
        }
        System.out.println();
    }

    public void updateLuxuryCar(Statement statement, Scanner userInput, String regNum, Luxury car){
        String answer = regNum;
        System.out.println("what do you want to update?\n" +
                "1 for regNumb\n2 for brand\n3 for model\n4 for regDate\n5 for kmDriven" +
                "\n6 for Over 3000CCM\n7 for aircondition\n8 for AutomaticGear\n9 for LeatherSeats");

        int ans = userInput.nextInt();

        boolean check = false;
        String newValue = null;
        String dbColumn = null;

        switch (ans){

            case 1 :
                System.out.println("Not valid try again");/*
                check = true;
                System.out.println("Enter new registration number");
                newValue = userInput.next();
                dbColumn = "registration_number";
                car.setRegistrationNumber(newValue);*/
                break;
            case 2 :
                check = true;
                System.out.println("Enter new brand");
                newValue =  userInput.next();
                dbColumn = "brand";
                car.setBrand(newValue);
                break;
            case 3 :
                check = true;
                System.out.println("Enter new model");
                newValue = userInput.next();
                dbColumn = "model";
                car.setModel(newValue);
                break;
            case 4 :
                check = true;
                System.out.println("Enter new registration date");
                newValue = userInput.next();
                dbColumn = "registration_date";
                car.setRegistrationDate(newValue);
                break;
            case 5 :
                check = true;
                System.out.println("Enter new km driven");
                int newKm = userInput.nextInt();
                newValue = String.valueOf(newKm);
                dbColumn = "km_driven";
                car.setKmDriven(newKm);
                break;
            case 6 :
                check = false;
                System.out.println("Enter new gear type - does it have Over 3000CCM");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")){
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                dbColumn = "ccm";
                car.setOver3000CCM(Boolean.parseBoolean(newValue));
                break;
            case 7 :
                check = false;
                System.out.println("Enter gear status - does it have automatic gear?");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")){
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                dbColumn = "automatic_gear";
                car.setAutomaticGear(Boolean.parseBoolean(newValue));
                break;
            case 8 :
                check = false;
                System.out.println("Enter new cruise control status - does it have cruise control?");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")){
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                dbColumn = "cruise_control";
                car.setCruiseControl(Boolean.parseBoolean(newValue));
                break;
            case 9 :
                check = false;
                System.out.println("Enter new info, does it have Leather Seats");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")){
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                dbColumn = "leather_seats";
                car.setLeatherSeats(Boolean.parseBoolean(newValue)); // gh
                break;
        }
        if(check == true) {
            dbCarRepo.updateCar(statement,dbColumn,newValue,answer);
        }
        if (check == false) {
            dbCarRepo.updateLuxuryCar(statement,dbColumn,newValue,answer);
        }
    }

    public void deleteLuxuryCar(Statement statement, ArrayList<Car> carList, String answer, UITools tools){
        dbCarRepo.deleteLuxuryCar(statement, carList, answer);
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(answer)) {
                carList.remove(carList.get(i));
                tools.customizedButton(60, 1, "Car \" + answer + \" is deleted");
            }
        }
    }
}
