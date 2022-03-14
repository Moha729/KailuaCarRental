package service;

import UI.MoTools;
import models.Car;
import models.Luxury;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class LuxuryService {
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

            String sql = ("SELECT * FROM car_table LEFT JOIN luxury_cars ON car_table.registration_number = luxury_cars.registration_number");
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null)
                while (resultSet.next()) {
                    luxury = new Luxury(
                            resultSet.getString("registration_number"),
                            resultSet.getString("brand"),
                            resultSet.getString("model"),
                            resultSet.getString("registration_date"),
                            resultSet.getInt("kmDriven"),
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
    public void viewLuxuryCars(ArrayList<Car> carList, MoTools tools){
        System.out.println();
        tools.customizedButton(50,1, "Luxury");

        tools.margeTop(70);
        System.out.printf("\n| %-14s %-14s %-12s %-12s %-12s %-10s %-10s %-13s %-13s |\n",
                "RegNumb", "Brand", "Model", "RegDate", "kmdriven", "Over 3000CCM", "AutomaticGear", "Cruise Control", "Leather Seats");
        tools.margeTop(120);

        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getClass().getSimpleName().equals("Luxury")) {
                System.out.println("\n" + carList.get(i).toString());
                tools.margeTop(120);
            }
        }
        System.out.println();
    }
    public void deleteLuxury(Statement statement,ArrayList<Car> carList) throws SQLException {
        System.out.println("Enter a registration number to delete its car information");
        String answer = userInput.next();
        statement.execute("DELETE FROM car_table WHERE registration_number = '"+answer+"'");
        statement.execute("DELETE FROM luxury_cars WHERE registration_number = '"+answer+"'");
        for (int i = 0; i < carList.size()-1; i++) {
            if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(answer));
            carList.remove(i).getRegistrationNumber().equalsIgnoreCase(answer);
        }
    }
}
