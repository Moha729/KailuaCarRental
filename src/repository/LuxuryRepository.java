package repository;

import UI.UITools;
import models.Car;
import models.Luxury;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class LuxuryRepository {

    DBCarRepo dbCarRepo = new DBCarRepo();



    public Luxury createLuxury(String reg, String br, String mo,
                               String regDate, int kmDr, UITools tools) {
        boolean ccm;
        boolean gear;
        boolean cruiseControl;
        boolean leatherSeats;

        ccm = tools.returnStringInfo(60, 1, "does it have over 3000CCM?").equalsIgnoreCase("yes");
        gear = tools.returnStringInfo(60, 1, "does it have automatic gear?").equalsIgnoreCase("yes");
        cruiseControl = tools.returnStringInfo(60, 1, "does it have cruise control?").equalsIgnoreCase("yes");
        leatherSeats = tools.returnStringInfo(60, 1, "does it have leather seats?").equalsIgnoreCase("yes");

        Luxury luxuryCar = new Luxury(reg, br, mo, regDate, kmDr, ccm, gear, cruiseControl, leatherSeats);

        addLuxuryCarToDB(luxuryCar);

        return luxuryCar;
    }

    public void populateLuxuryToArrayList(ArrayList<Car> carList) {
        dbCarRepo.populateLuxuryCarToArraylist(carList);
    }

    public void addLuxuryCarToDB(Luxury luxuryCar) {
        dbCarRepo.addLuxuryCarToDB(luxuryCar);
    }

    public void viewLuxuryCars(ArrayList<Car> carList, UITools tools) {
        System.out.println();
        tools.customizedButton(50, 1, "Luxury");

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

    public void updateLuxuryCar(Scanner userInput, Luxury car, UITools tools) {
        tools.customizedButton(60,2, "What do you want to update?");
        System.out.println();
        tools.customizedButton(39,3,"" +
                "\t\t1 for Brand\t\t\t\t\t\t|" +
                "\n|\t\t2 for Model\t\t\t\t\t\t|" +
                "\n|\t\t3 for Registration date\t\t\t|\n|\t\t4 KM driven\t\t\t\t\t\t|"+
                "\n|\t\t5 for CCM info\t\t\t\t\t|\n|\t\t6 for Air-condition\t\t\t\t|"+
                "\n|\t\t7 for Automatic gear\t\t\t|\n|\t\t6 for Fabric of seats\t\t\t");

        int ans = userInput.nextInt();

        boolean check = false;
        String newValue = null;
        String dbColumn = null;

        switch (ans) {


            case 1:
                check = true;
                tools.customizedButton(60,1,"Enter new brand");
                newValue = userInput.next();
                dbColumn = "brand";
                car.setBrand(newValue);
                break;
            case 2:
                check = true;
                tools.customizedButton(60,1,"Enter new model");
                newValue = userInput.next();
                dbColumn = "model";
                car.setModel(newValue);
                break;
            case 3:
                check = true;
                tools.customizedButton(60,1,"Enter new registration date");
                newValue = userInput.next();
                dbColumn = "registration_date";
                car.setRegistrationDate(newValue);
                break;
            case 4:
                check = true;
                tools.customizedButton(60,1,"Enter new km driven");
                int newKm = userInput.nextInt();
                newValue = String.valueOf(newKm);
                dbColumn = "km_driven";
                car.setKmDriven(newKm);
                break;
            case 5:
                check = false;
                tools.customizedButton(60,1,"Enter new gear type - does it have Over 3000CCM");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")) {
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                dbColumn = "ccm";
                car.setOver3000CCM(Boolean.parseBoolean(newValue));
                break;
            case 6:
                check = false;
                tools.customizedButton(60,1,"Enter gear status - does it have automatic gear?");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")) {
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                dbColumn = "automatic_gear";
                car.setAutomaticGear(Boolean.parseBoolean(newValue));
                break;
            case 7:
                check = false;
                tools.customizedButton(60,1,"Enter new cruise control status - does it have cruise control?");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")) {
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                dbColumn = "cruise_control";
                car.setCruiseControl(Boolean.parseBoolean(newValue));
                break;
            case 8:
                check = false;
                tools.customizedButton(60,1,"Enter new info, does it have Leather Seats");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")) {
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                dbColumn = "leather_seats";
                car.setLeatherSeats(Boolean.parseBoolean(newValue)); // gh
                break;
        }
        if (check == true) {
            dbCarRepo.updateCar(dbColumn, newValue, car.getRegistrationNumber());
        }
        if (check == false) {
            dbCarRepo.updateAllCar(dbColumn, newValue, car.getRegistrationNumber(), "luxury_cars");
        }
    }

    public void deleteLuxuryCar(ArrayList<Car> carList, UITools tools, Car car) {
        dbCarRepo.deleteAllCar(car.getRegistrationNumber(), "luxury_cars");
        carList.remove(car);
        tools.customizedButton(60, 1, "Car " + car.getRegistrationNumber() + " is deleted");
    }
}
