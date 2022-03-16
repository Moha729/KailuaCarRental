package repository;

import UI.UITools;
import models.Car;
import models.Luxury;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class LuxuryRepository {

    Scanner userInput = new Scanner(System.in);
    Luxury luxury = new Luxury();



    public Luxury createLuxury(Statement statement, ArrayList<Car> carList, Scanner userInput, String  reg, String br, String mo,
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

        System.out.println("pres 1 to continue");

        return luxuryCar;
    }

    public void addLuxuryCarToDB(Luxury luxuryCar, Statement statement) throws SQLException {

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

    public void populateLuxuryToArrayList(Statement statement,ArrayList<Car> carList) { // table content
        try {

            String sql = ("SELECT * FROM car_table INNER JOIN luxury_cars ON car_table.registration_number = luxury_cars.registration_number");
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null)
                while (resultSet.next()) {
                    luxury = new Luxury(
                            resultSet.getString("registration_number"),
                            resultSet.getString("brand"),
                            resultSet.getString("model"),
                            resultSet.getString("registration_date"),
                            resultSet.getInt("km_driven"),
                            resultSet.getBoolean("ccm"),
                            resultSet.getBoolean("automatic_gear"),
                            resultSet.getBoolean("cruise_control"),
                            resultSet.getBoolean("leather_seats"));

                    carList.add(luxury);
                }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
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

    public void updateLuxuryCar(Statement statement, ArrayList<Car> carList, Scanner userInput, String regNum, Car car){

        /*/for (int i = 0; i < carList.size(); i++) {System.out.println(carList.get(i));}
        String statementService = userInput.next();
        statementService = "cruise_control";
        String cruise = userInput.next();
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(answer))
                System.out.println(carList.get(i));
        }
        //System.out.println("Enter which registration number to be updated");*/
        String answer = regNum;



        System.out.println("what do you want to update?\n" +
                "1 for regNumb\n2 for brand\n3 for model\n4 for regDate\n5 for kmDriven" +
                "\n6 for Over 3000CCM\n7 for aircondition\n 8 for AutomaticGear\n9 for LeatherSeats");

        int ans = userInput.nextInt();


        boolean extention = true;
        String newValue = null;
        String newVariable = null;

        switch (ans){

            case 1 :
                System.out.println("Enter new registration number");
                newValue = userInput.next();
                newVariable = "registration_number";
                car.setRegistrationNumber(newValue);
                break;
            case 2 :
                System.out.println("Enter new brand");
                newValue =  userInput.next();
                newVariable = "brand";
                car.setBrand(newValue);
                break;
            case 3 :
                System.out.println("Enter new model");
                newValue = userInput.next();
                newVariable = "model";
                car.setModel(newValue);
                break;
            case 4 :
                System.out.println("Enter new registration date");
                newValue = userInput.next();
                newVariable = "registration_date";
                car.setRegistrationDate(newValue);
                break;
            case 5 :
                System.out.println("Enter new km driven");
                int newKm = userInput.nextInt();
                newValue = String.valueOf(newKm);
                newVariable = "km_driven";
                car.setKmDriven(newKm);
                break;
            case 6 :
                extention = false;
                System.out.println("Enter new gear type - does it have Over 3000CCM");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")){
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                newVariable = "manual_gear";

                break;
            case 7 :
                extention = false;
                System.out.println("Enter new air condition status - does it have air condition?");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")){
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                newVariable = "air_condition";
                break;
            case 8 :
                extention = false;
                System.out.println("Enter new cruise control status - does it have Automatic Gear?");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")){
                    newValue = "true";
                } else {
                    newValue = "false";
                }
                newVariable = "cruise_control";
                break;
            case 9 :
                extention = false;
                System.out.println("Enter new info, does it have Leather Seats");
                newValue = userInput.next();
                if (newValue.equalsIgnoreCase("yes")){
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
                System.out.println("Could not update luxury table");
            }
        }
        //statement.close();
    }


}
