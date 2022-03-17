package repository;

import UI.UITools;
import models.Car;
import models.Sport;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class SportRepository {


    UITools tools = new UITools();
    DBCarRepo dbRepo = new DBCarRepo();

    public Sport createSportsCar(Statement statement, String reg, String br, String mo,
                                 String regDate, int kmDr) throws SQLException {

        String gear = this.tools.returnStringInfo(60, 1, "does it have a manual gear?");
        boolean gearGear;
        if (gear.equalsIgnoreCase("yes")) {
            gearGear = true;
        } else {
            gearGear = false;
        }

        String horsePower = this.tools.returnStringInfo(60, 1, "does it have over 200 hP?");
        boolean hpHp;
        if (horsePower.equalsIgnoreCase("Yes")) {
            hpHp = true;
        } else {
            hpHp = false;
        }

        Sport sportsCar = new Sport(reg, br, mo, regDate, kmDr, gearGear, hpHp);

        addSportCarToDB(sportsCar, statement);

        System.out.println("pres 1 to continue");

        return sportsCar;
    }


    public void populateSportToArrayList(Statement statement, ArrayList<Car> carList) { // table content
      dbRepo.populateSportToArrayList(statement,carList);
    }

    private void addSportCarToDB(Sport sportsCar, Statement statement) throws SQLException {
        dbRepo.addSportCarToDB(sportsCar,statement);
    }

    public void viewSportCars(ArrayList<Car> carList, UITools tools) {
        System.out.println();
        tools.customizedButton(50, 1, "Sports");

        tools.margeTop(40);
        System.out.printf("\n| %-14s %-14s %-12s %-12s %-12s %-10s %-10s |\n",
                "RegNumb", "Brand", "Model", "RegDate", "kmdriven", "manu-gear", "Over200HP.");
        tools.margeTop(91);

        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getClass().getSimpleName().equals("Sport")) {
                System.out.println("\n" + carList.get(i).toString());
                tools.margeTop(91);
            }
        }
        System.out.println();
    }

    public void updateSportCar(Statement statement, Scanner userInput, String regNum, Sport car) {

        System.out.println("Enter which registration number to be updated");
        String answer = regNum;


        System.out.println("what do you want to update?\n" +
                "1 for regNumb\n2 for brand\n3 for model\n4 for regDate\n5 for kmDriven" +
                "\n6 for manualGear\n7 for Over 200 HP");

        int ans = userInput.nextInt();

        boolean check = false;
        String newValue = null;
        String dbColumn = null;

        switch (ans) {

            case 1:
                check = true;
                System.out.println("Enter new registration number");
                newValue = userInput.next();
                dbColumn = "registration_number";
                car.setRegistrationNumber(newValue);
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
                System.out.println("Does it have over 200 hp?");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")) {
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                dbColumn = "over200HP";
                car.setOver200HP(Boolean.parseBoolean(newValue));
                break;
        }
        if (check == true) {
            dbRepo.updateCar(statement, dbColumn, newValue, answer);

        }
        if (check == false) {
            dbRepo.updateSportCar(statement, dbColumn, newValue, answer);
        }
    }
}
