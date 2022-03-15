package service;

import UI.MoTools;
import models.Car;
import models.Luxury;
import models.Sport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class SportService {
    MoTools tools = new MoTools();
    Scanner userInput = new Scanner(System.in);
    Sport sport;

    public Sport createSportsCar(Statement statement, Scanner userInput, ArrayList<Car> carList, String reg, String br, String mo,
                                 String regDate, int kmDr) throws SQLException {

        String gear = tools.returnStringInfo(50, 1, "does it have a manual gear?");
        boolean gearGear;
        if (gear.equalsIgnoreCase("yes")) {
            gearGear = true;
        } else {
            gearGear = false;
        }

        String horsePower = tools.returnStringInfo(50, 1, "does it have over 200 hP?");
        boolean hpHp;
        if (horsePower.equalsIgnoreCase("Yes")) {
            hpHp = true;
        } else {
            hpHp = false;
        }

        Sport sportsCar = new Sport(reg, br, mo, regDate, kmDr, gearGear, hpHp);

        addSportsCarToDB(sportsCar, statement);

        System.out.println("pres 1 to continue");

        return sportsCar;
    }


    public void populateSportToArrayList(Statement statement,ArrayList<Car> carList) { // table content
        try {

            String sql = ("SELECT * FROM car_table INNER JOIN sport_cars ON car_table.registration_number = sport_cars.registration_number");
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null)
                while (resultSet.next()) {
                    sport = new Sport(
                            resultSet.getString("registration_number"),
                            resultSet.getString("brand"),
                            resultSet.getString("model"),
                            resultSet.getString("registration_date"),
                            resultSet.getInt("km_driven"),
                            resultSet.getBoolean("manual_gear"),
                            resultSet.getBoolean("over200HP"));


                    carList.add(sport);
                }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }

    private void addSportsCarToDB(Sport sportsCar, Statement statement) throws SQLException {
        statement.execute("INSERT INTO car_table " + "(registration_number, brand, model, registration_date, km_driven)" + "" +
                "VALUES('"
                + sportsCar.getRegistrationNumber() + "','"
                + sportsCar.getBrand() + "','"
                + sportsCar.getModel() + "','"
                + sportsCar.getRegistrationDate() + "','"
                + sportsCar.getKmDriven() + "')");

        statement.execute("INSERT INTO  sport_cars " + "(registration_number, manual_gear, Over200HP)" + "" +
                "VALUES('"
                + sportsCar.getRegistrationNumber() + "','"
                + sportsCar.isManualGear() + "','"
                + sportsCar.isOver200HP() + "')");
    }


    public void updateSportCar(Statement statement, ArrayList<Car> carList, Scanner userInput){

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
                "\n6 for manualGear\n7 for Over 200 HP");

        int ans = userInput.nextInt();

        String statementService = userInput.next();
        statementService = "cruise_control";
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
            case 3 :
                System.out.println("Enter new model");
                newValue = userInput.next();
                newVariable = "model";
                break;
            case 4 :
                System.out.println("Enter new registration date");
                newValue = userInput.next();
                newVariable = "registration_date";
                break;
            case 5 :
                System.out.println("Enter new km driven");
                newValue = userInput.next();
                newVariable = "km_driven";
                break;
            case 6 :
                System.out.println("Enter new gear type - does it have manual gear?");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")){
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                newVariable = "manual_gear";
                break;
            case 7 :
                System.out.println("Enter new air condition status - does it have air condition?");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")){
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                newVariable = "Over 200 HP";
                break;
        }

        try {
            statement.execute("UPDATE car_table SET " +
                    newVariable + " = '" + newValue + "' " +
                    "WHERE registration_number ='" + answer + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update sport table");
        }

        try {
            statement.execute("UPDATE family_cars SET " +
                    newVariable + " = '" + newValue + "' " +
                    "WHERE registration_number ='" + answer + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update sport table");
        }
        //statement.close();
    }


    public void viewSportCars(ArrayList<Car> carList, MoTools tools) {
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

    public void deleteSport(Statement statement,ArrayList<Car> carList) throws SQLException {
        System.out.println("Enter a registration number to delete its car information");
        String answer = userInput.next();
        statement.execute("DELETE FROM car_table WHERE registration_number = '"+answer+"'");
        statement.execute("DELETE FROM sport_cars WHERE registration_number = '"+answer+"'");
        for (int i = 0; i < carList.size()-1; i++) {
            if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(answer));
            carList.remove(i).getRegistrationNumber().equalsIgnoreCase(answer);
        }
    }
}
