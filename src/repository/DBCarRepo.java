package repository;


import db.DBManager;
import models.Car;
import models.Family;
import models.Luxury;
import models.Sport;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DBCarRepo {
    Connection connection = DBManager.getConnection();


    public void populateFamilyCarToArraylist(ArrayList<Car> carList) {
        try {
            String sql = ("SELECT * FROM car_table INNER JOIN family_cars ON car_table.registration_number = family_cars.registration_number");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            if (resultSet != null)
                while (resultSet.next()) {
                    Family family = new Family(
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
            preparedStatement.executeUpdate();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }

    public void populateLuxuryCarToArraylist(ArrayList<Car> carList) {
        try {
            String sql = ("SELECT * FROM car_table INNER JOIN luxury_cars ON car_table.registration_number = luxury_cars.registration_number");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            if (resultSet != null)
                while (resultSet.next()) {
                    Luxury luxury = new Luxury(
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
            preparedStatement.executeUpdate();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }

    public void populateSportToArrayList(ArrayList<Car> carList) {
        try {
            String sql = ("SELECT * FROM car_table INNER JOIN sport_cars ON car_table.registration_number = sport_cars.registration_number");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            if (resultSet != null)
                while (resultSet.next()) {
                    Sport sport = new Sport(
                            resultSet.getString("registration_number"),
                            resultSet.getString("brand"),
                            resultSet.getString("model"),
                            resultSet.getString("registration_date"),
                            resultSet.getInt("km_driven"),
                            resultSet.getBoolean("manual_gear"),
                            resultSet.getBoolean("over200HP"));
                    carList.add(sport);
                }
            preparedStatement.executeUpdate();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }

    public void addFamilyCarToDB(Family familyCar, Statement statement) {
        try {
            statement.execute("INSERT INTO car_table " + "(registration_number,brand,model, registration_date, km_driven)" + "" +
                    "VALUES('"
                    + familyCar.getRegistrationNumber() + "','"
                    + familyCar.getBrand() + "','"
                    + familyCar.getModel() + "','"
                    + familyCar.getRegistrationDate() + "','"
                    + familyCar.getKmDriven() + "')");

            statement.execute("INSERT INTO  family_cars " + "(registration_number, manual_gear ,air_condition , cruise_control, seven_seats_or_more)" + "" +
                    "VALUES('"
                    + familyCar.getRegistrationNumber() + "','"
                    + familyCar.isManualGear() + "','"
                    + familyCar.isAirCondition() + "','"
                    + familyCar.isCruiseControl() + "','"
                    + familyCar.isSevenSeatsOrMore() + "')");

        } catch (SQLException sqlException) {
            System.out.println("Error adding family to database: " + sqlException);
            System.exit(1);
        }
    }

    public void addLuxuryCarToDB(Luxury luxuryCar, Statement statement) {
        try {
            statement.execute("INSERT INTO car_table " + "(registration_number,brand,model, registration_date, km_driven)" + "" +
                    "VALUES('"
                    + luxuryCar.getRegistrationNumber() + "','"
                    + luxuryCar.getBrand() + "','"
                    + luxuryCar.getModel() + "','"
                    + luxuryCar.getRegistrationDate() + "','"
                    + luxuryCar.getKmDriven() + "')");
            /*System.out.println("Stop here");
            Scanner scanner = new Scanner(System.in);
            String go = scanner.next();*/
            statement.execute("INSERT INTO  luxury_cars " + "(registration_number, ccm, automatic_gear, cruise_control, leather_seats)" + "" +
                    "VALUES('"
                    + luxuryCar.getRegistrationNumber() + "','"
                    + luxuryCar.isOver3000CCM() + "','"
                    + luxuryCar.isAutomaticGear() + "','"
                    + luxuryCar.isCruiseControl() + "','"
                    + luxuryCar.isLeatherSeats() + "')");
        } catch (SQLException e) {
            System.out.println("No cars added");
        }
    }

    public void addSportCarToDB(Sport sportsCar, Statement statement) {
        try {
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
        } catch (SQLException e) {
            System.out.println("No cars added");
        }
    }

    public void deleteAllCar(Statement statement, String answer, String sqlTable_name){
        try {
            statement.execute("DELETE FROM car_table WHERE registration_number = '" + answer + "'");
            statement.execute("DELETE FROM "+ sqlTable_name +" WHERE registration_number = '" + answer + "'");
        } catch (SQLException e) {
            System.out.println("No cars deleted");
        }
    }

    public void updateCar(Statement statement, String newVariable, String newValue, String answer) {
        try {
            statement.execute("UPDATE car_table SET " +
                    newVariable + " = '" + newValue + "' " +
                    "WHERE registration_number ='" + answer + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update car table");
        }
    }

    public void updateAllCar(Statement statement, String newVariable, String newValue, String answer,
                             String sqlTable_name) {
        try {
            statement.execute("UPDATE " + sqlTable_name +" SET " +
                    newVariable + " = '" + newValue + "' " +
                    "WHERE registration_number ='" + answer + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update sport car");
        }
    }
}
